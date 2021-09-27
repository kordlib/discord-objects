package dev.kord.discord.objects.serializer

import dev.kord.discord.objects.ApplicationCommandOptionType
import dev.kord.discord.objects.Option
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.decodeStructure

internal abstract class CommandSerializer<T : Option, R : Any>(
    name: String,
    protected val optionsSerializer: KSerializer<R>
) : KSerializer<T> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(name) {
        element<String>("name")
        element("type", ApplicationCommandOptionType.serializer().descriptor)
        element("options", optionsSerializer.descriptor, isOptional = true)
    }

    abstract fun create(name: String, options: R): T

    override fun deserialize(decoder: Decoder): T {
        lateinit var name: String
        lateinit var option: R
        decoder.decodeStructure(descriptor) {
            while (true) {
                when (this.decodeElementIndex(descriptor)) {
                    0 -> name = decodeStringElement(descriptor, 0)
                    1 -> decodeSerializableElement(descriptor, 1, ApplicationCommandOptionType.serializer())
                    2 -> option = decodeSerializableElement(descriptor, 2, optionsSerializer)
                    CompositeDecoder.DECODE_DONE -> break
                }
            }
        }

        return create(name, option)
    }

}
