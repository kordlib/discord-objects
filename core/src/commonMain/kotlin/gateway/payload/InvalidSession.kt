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
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*

@Serializable(with = InvalidSession.Serializer::class)
data class InvalidSession(
    val data: Boolean?
) : Event {

    override val opcode: Opcode
        get() = Opcode.InvalidSession

    internal object Serializer : KSerializer<InvalidSession> {

        override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
            "dev.kord.discord.objects.gateway.payload.InvalidSession"
        ) {
            element("op", Opcode.serializer().descriptor)
            element<Boolean>("d")
            element<Unit>("t", isOptional = true)
            element<Unit>("s", isOptional = true)
        }

        @OptIn(ExperimentalSerializationApi::class)
        override fun deserialize(decoder: Decoder): InvalidSession = decoder.decodeStructure(descriptor) {
            var data: Boolean? = null
            while (true) {
                when (decodeElementIndex(descriptor)) {
                    0 -> {
                        val opcode = decodeSerializableElement(descriptor, 0, Opcode.serializer())
                        if (opcode != Opcode.InvalidSession) throw SerializationException(
                            "Heartbeat opcode expected but got ${opcode.code}"
                        )
                    }
                    1 -> data = decodeNullableSerializableElement(descriptor, 1, Boolean.serializer().nullable)
                    2 -> decodeNullableSerializableElement(descriptor, 2, Unit.serializer().nullable)
                    3 -> decodeNullableSerializableElement(descriptor, 3, Unit.serializer().nullable)
                    CompositeDecoder.DECODE_DONE -> break
                    CompositeDecoder.UNKNOWN_NAME -> throw SerializationException(
                        "unknown elements are not supported for Hello Serializer"
                    )
                }
            }

            return InvalidSession(data)
        }

        @OptIn(ExperimentalSerializationApi::class)
        override fun serialize(encoder: Encoder, value: InvalidSession) = encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, Opcode.serializer(), value.opcode)
            encodeNullableSerializableElement(descriptor, 1, Boolean.serializer(), value.data)
            encodeNullableSerializableElement(descriptor, 2, Unit.serializer(), null)
            encodeNullableSerializableElement(descriptor, 3, Unit.serializer(), null)
        }

    }

}
