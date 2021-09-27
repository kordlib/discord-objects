package dev.kord.discord.objects.serializer

import dev.kord.discord.objects.ApplicationCommandOptionType
import dev.kord.discord.objects.CommandArgument
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlinx.serialization.serializer

@Suppress("FunctionName")
internal inline fun <reified D : Any, reified T : CommandArgument<D>> GenericCommandArgument(
    noinline constructor: (String, D) -> T
): KSerializer<T> = GenericCommandArgument(T::class.simpleName!!, serializer(), constructor)

internal class GenericCommandArgument<D : Any, T : CommandArgument<D>>(
    name: String,
    private val valueSerializer: KSerializer<D>,
    private val constructor: (String, D) -> T
) : KSerializer<T> {

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
        "dev.kord.discord.objects.$name"
    ) {
        element<ApplicationCommandOptionType>("type")
        element<String>("name")
        element("value", valueSerializer.descriptor)
    }

    @Suppress("UNCHECKED_CAST")
    override fun deserialize(decoder: Decoder): T {
        lateinit var type: ApplicationCommandOptionType
        lateinit var name: String
        lateinit var value: D

        decoder.decodeStructure(descriptor) {
            while (true) {
                when (decodeElementIndex(descriptor)) {
                    0 -> type = decodeSerializableElement(descriptor, 0, ApplicationCommandOptionType.serializer())
                    1 -> name = decodeStringElement(descriptor, 1)
                    2 -> value = decodeSerializableElement(descriptor, 2, valueSerializer)
                    CompositeDecoder.DECODE_DONE -> break
                    CompositeDecoder.UNKNOWN_NAME -> error("unknown index")
                }
            }
        }

        return constructor(name, value)
    }

    override fun serialize(encoder: Encoder, value: T) = encoder.encodeStructure(descriptor) {
        encodeSerializableElement(descriptor, 0, ApplicationCommandOptionType.serializer(), value.type)
        encodeStringElement(descriptor, 1, value.name)
        encodeSerializableElement(descriptor, 2, valueSerializer, value.value)
    }

}
