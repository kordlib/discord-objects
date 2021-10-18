package dev.kord.discord.objects.gateway.payload.serializer

import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.*
import kotlinx.serialization.ContextualSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure

internal object EventSerializer : KSerializer<Event> {

    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("dev.kord.discord.objects.gateway.payload.Event") {
            element("t", EventName.serializer().descriptor, isOptional = true)
            element("s", Int.serializer().descriptor, isOptional = true)
            element("op", Opcode.serializer().descriptor, isOptional = false)
            element("d", Unit.serializer().descriptor, isOptional = true)
        }

    @OptIn(ExperimentalSerializationApi::class)
    internal fun CompositeDecoder.decodeEventAtDataIndex(
        descriptor: SerialDescriptor,
        index: Int,
        name: EventName?,
        sequence: Int?,
        opcode: Opcode
    ): Event = when (opcode) {
        Opcode.Dispatch -> with(DispatchSerializer.Companion) {
            requireNotNull(name) { "'t' field is required before 'd' field" }
            requireNotNull(sequence) { "'s' field is required before 'd' field" }
            decodeDispatchAtDataIndex(descriptor, index, name, sequence)
        }
        Opcode.Heartbeat -> Heartbeat(
            decodeNullableSerializableElement(descriptor, index, Int.serializer().nullable)
        )
        Opcode.HeartbeatACK -> {
            //decode null
            decodeNullableSerializableElement(descriptor, index, Unit.serializer().nullable)
            HeartbeatAck
        }
        Opcode.Hello -> Hello(
            decodeSerializableElement(descriptor, index, Hello.Data.serializer())
        )
        Opcode.InvalidSession -> InvalidSession(
            decodeNullableSerializableElement(descriptor, index, Boolean.serializer().nullable)
        )
        Opcode.Reconnect -> {
            //decode null
            decodeNullableSerializableElement(descriptor, index, Unit.serializer().nullable)
            Reconnect
        }
        Opcode.VoiceStateUpdate,
        Opcode.Identify,
        Opcode.RequestGuildMembers,
        Opcode.Resume,
        Opcode.StatusUpdate -> throw SerializationException(
            "Opcode $opcode is not an event opcode."
        )
        is Opcode.Unknown -> throw SerializationException(
            "Unknown opcode $opcode cannot be deserialized."
        )
    }

    override fun deserialize(decoder: Decoder): Event = decoder.decodeStructure(descriptor) {
        var name: EventName? = null
        var sequence: Int? = null
        var opcode: Opcode? = null

        var event: Event? = null

        while (true) {
            when (val index = decodeElementIndex(descriptor)) {
                0 -> {
                    name = decodeSerializableElement(descriptor, index, EventName.serializer())
                    require(name !is EventName.Unknown) {
                        "Unknown event name: $name cannot be deserialized"
                    }
                }
                1 -> sequence = decodeIntElement(descriptor, index)
                2 -> opcode = decodeSerializableElement(descriptor, index, Opcode.serializer())
                3 -> {
                    requireNotNull(opcode) { "'op' field is required before 'd' field" }
                    event = decodeEventAtDataIndex(descriptor, index, name, sequence, opcode)
                }
                CompositeDecoder.DECODE_DONE -> break
            }
        }

        return when {
            event != null -> event
            opcode == null -> throw SerializationException(
                "'op' field is required"
            )
            opcode is Opcode.Unknown -> throw SerializationException(
                "Unknown opcode $opcode cannot be deserialized."
            )
            opcode == Opcode.HeartbeatACK -> HeartbeatAck
            opcode == Opcode.Reconnect -> Reconnect
            else -> throw SerializationException(
                "opcode $opcode requires a 'd' field"
            )
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: Event) = when (value) {
        is Dispatch<*> -> Dispatch.genericSerializer().serialize(encoder, value)
        is Heartbeat -> Heartbeat.serializer().serialize(encoder, value)
        is HeartbeatAck -> HeartbeatAck.serializer().serialize(encoder, value)
        is Hello -> Hello.serializer().serialize(encoder, value)
        is InvalidSession -> InvalidSession.serializer().serialize(encoder, value)
        is Reconnect -> Reconnect.serializer().serialize(encoder, value)
        is UnknownEvent -> ContextualSerializer(UnknownEvent::class).serialize(encoder, value)
    }

}
