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

@Serializable(with = HeartbeatAck.Serializer::class)
object HeartbeatAck : Event {
    override val opcode: Opcode
        get() = Opcode.HeartbeatACK

    override fun equals(other: Any?): Boolean {
        return other is HeartbeatAck
    }

    override fun toString(): String = "HeartbeatAck"

    fun serializer(): KSerializer<HeartbeatAck> = Serializer

    internal object Serializer : KSerializer<HeartbeatAck> {

        override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
            "dev.kord.discord.objects.gateway.payload.HeartbeatAck",
        ) {
            element("op", Opcode.serializer().descriptor)
            element<Unit>("d", isOptional = true)
            element<Unit>("t", isOptional = true)
            element<Unit>("s", isOptional = true)
        }

        @OptIn(ExperimentalSerializationApi::class)
        override fun deserialize(decoder: Decoder): HeartbeatAck = decoder.decodeStructure(descriptor) {
            while (true) {
                when (decodeElementIndex(descriptor)) {
                    0 -> {
                        val opcode = decodeSerializableElement(descriptor, 0, Opcode.serializer())
                        if (opcode != Opcode.HeartbeatACK) throw SerializationException(
                            "Heartbeat opcode expected but got ${opcode.code}"
                        )
                    }
                    1 -> decodeNullableSerializableElement(descriptor, 1, Unit.serializer().nullable)
                    2 -> decodeNullableSerializableElement(descriptor, 2, Unit.serializer().nullable)
                    3 -> decodeNullableSerializableElement(descriptor, 3, Unit.serializer().nullable)
                    CompositeDecoder.DECODE_DONE -> break
                    CompositeDecoder.UNKNOWN_NAME -> throw SerializationException(
                        "unknown elements are not supported for Hello Serializer"
                    )
                }
            }

            return HeartbeatAck
        }

        @OptIn(ExperimentalSerializationApi::class)
        override fun serialize(encoder: Encoder, value: HeartbeatAck) = encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, Opcode.serializer(), value.opcode)
            encodeNullableSerializableElement(descriptor, 1, Unit.serializer(), null)
            encodeNullableSerializableElement(descriptor, 2, Unit.serializer(), null)
            encodeNullableSerializableElement(descriptor, 3, Unit.serializer(), null)
        }

    }
}
