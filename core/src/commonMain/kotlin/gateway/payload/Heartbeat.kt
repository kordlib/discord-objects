package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.gateway.Opcode
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*

@Serializable(with = Heartbeat.Serializer::class)
data class Heartbeat(
    override val data: Int?
) : Event, Command<Int?> {

    override val opcode: Opcode get() = Opcode.Heartbeat

    internal object Serializer : KSerializer<Heartbeat> {

        override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
            "dev.kord.discord.objects.gateway.payload.Heartbeat"
        ) {
            element("op", Opcode.serializer().descriptor)
            element<Hello.Data>("d")
            element<Unit>("t", isOptional = true)
            element<Unit>("s", isOptional = true)
        }

        @OptIn(ExperimentalSerializationApi::class)
        override fun deserialize(decoder: Decoder): Heartbeat = decoder.decodeStructure(descriptor) {
            var data: Int? = null
            while (true) {
                when (decodeElementIndex(descriptor)) {
                    0 -> {
                        val opcode = decodeSerializableElement(descriptor, 0, Opcode.serializer())
                        if (opcode != Opcode.Heartbeat) throw SerializationException(
                            "Heartbeat opcode expected but got ${opcode.code}"
                        )
                    }
                    1 -> data = decodeNullableSerializableElement(descriptor, 1, Int.serializer().nullable)
                    2 -> decodeNullableSerializableElement(descriptor, 2, Unit.serializer().nullable)
                    3 -> decodeNullableSerializableElement(descriptor, 3, Unit.serializer().nullable)
                    CompositeDecoder.DECODE_DONE -> break
                    CompositeDecoder.UNKNOWN_NAME -> throw SerializationException(
                        "unknown elements are not supported for Hello Serializer"
                    )
                }
            }

            return Heartbeat(data)
        }

        @OptIn(ExperimentalSerializationApi::class)
        override fun serialize(encoder: Encoder, value: Heartbeat) = encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, Opcode.serializer(), value.opcode)
            encodeNullableSerializableElement(descriptor, 1, Int.serializer(), value.data)
            encodeNullableSerializableElement(descriptor, 2, Unit.serializer(), null)
            encodeNullableSerializableElement(descriptor, 3, Unit.serializer(), null)
        }

    }

}
