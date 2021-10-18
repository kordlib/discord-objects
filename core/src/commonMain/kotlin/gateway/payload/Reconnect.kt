package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.gateway.Opcode
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.*

@Serializable(with = Reconnect.Serializer::class)
object Reconnect : Event {
    override val opcode: Opcode
        get() = Opcode.Reconnect

    override fun equals(other: Any?): Boolean {
        return other is Reconnect
    }

    override fun toString(): String = "Reconnect"

    //workaround, compiler won't generate this because this is an object
    //for whatever reason, only when adding this, it makes the class serializable by default
    fun serializer() : KSerializer<Reconnect> = Serializer

    @OptIn(ExperimentalSerializationApi::class)
    internal object Serializer : KSerializer<Reconnect> {

        override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
            "dev.kord.discord.objects.gateway.payload.Reconnect"
        ) {
            element("op", Opcode.serializer().descriptor)
            element("d", Unit.serializer().descriptor, isOptional = true)
        }

        override fun deserialize(decoder: Decoder): Reconnect = decoder.decodeStructure(descriptor) {
            while (true) {
                when (decodeElementIndex(descriptor)) {
                    0 -> decodeSerializableElement(descriptor, 0, Opcode.serializer())
                    1 -> decodeNullableSerializableElement(descriptor, 1, Unit.serializer().nullable)
                    CompositeDecoder.DECODE_DONE -> break
                    CompositeDecoder.UNKNOWN_NAME -> throw SerializationException(
                        "unknown elements are not supported for Reconnect Serializer"
                    )
                }
            }

            return@decodeStructure Reconnect
        }

        override fun serialize(encoder: Encoder, value: Reconnect) = encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, Opcode.serializer(), value.opcode)
            encodeNullableSerializableElement(descriptor, 1, Unit.serializer(), null)
        }

    }
}
