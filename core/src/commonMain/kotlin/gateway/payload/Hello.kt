package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.gateway.Opcode
import kotlinx.serialization.*
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*

@Serializable(with = Hello.Serializer::class)
data class Hello(@SerialName("d") val data: Data) : Event {

    override val opcode: Opcode
        get() = Opcode.Hello

    @Serializable
    data class Data(@SerialName("heartbeat_interval") val heartbeatInterval: Long)

    internal object Serializer : KSerializer<Hello> {

        override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
            "dev.kord.discord.objects.gateway.payload.Hello"
        ) {
            element("op", Opcode.serializer().descriptor)
            element<Data>("d")
            element<Unit>("t", isOptional = true)
            element<Unit>("s", isOptional = true)
        }

        @OptIn(ExperimentalSerializationApi::class)
        override fun deserialize(decoder: Decoder): Hello = decoder.decodeStructure(descriptor) {
            lateinit var data: Data
            while (true) {
                when (decodeElementIndex(descriptor)) {
                    0 -> {
                        val opcode = decodeSerializableElement(descriptor, 0, Opcode.serializer())
                        if (opcode != Opcode.Hello) throw SerializationException(
                            "Heartbeat opcode expected but got ${opcode.value}"
                        )
                    }
                    1 -> data = decodeSerializableElement(descriptor, 1, Data.serializer())
                    2 -> decodeNullableSerializableElement(descriptor, 2, Unit.serializer().nullable)
                    3 -> decodeNullableSerializableElement(descriptor, 3, Unit.serializer().nullable)
                    CompositeDecoder.DECODE_DONE -> break
                    CompositeDecoder.UNKNOWN_NAME -> throw SerializationException(
                        "unknown elements are not supported for Hello Serializer"
                    )
                }
            }

            return Hello(data)
        }

        @OptIn(ExperimentalSerializationApi::class)
        override fun serialize(encoder: Encoder, value: Hello) = encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, Opcode.serializer(), value.opcode)
            encodeSerializableElement(descriptor, 1, Data.serializer(), value.data)
            encodeNullableSerializableElement(descriptor, 2, Unit.serializer(), null)
            encodeNullableSerializableElement(descriptor, 3, Unit.serializer(), null)
        }

    }
}
