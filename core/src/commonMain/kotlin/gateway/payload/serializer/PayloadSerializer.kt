package dev.kord.discord.objects.gateway.payload.serializer

import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
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

/**
 * Serializer for Gateway Payloads.
 *
 * Because kx.ser-core only allows in-order serialization, this serializer comes with the limitation that
 * the `d` (data) field must always be the last field in a payload, and must be preceded by the `t` (event name)
 * and `op` (opcode) field. This allows for the information needed to figure out the type of event being handled.
 */
@Suppress("NAME_SHADOWING")
@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal object PayloadSerializer : KSerializer<Payload> {

    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("dev.kord.discord.objects.gateway.payload.Payload") {
            element("t", EventName.serializer().descriptor, isOptional = true)
            element("s", Int.serializer().descriptor, isOptional = true)
            element("op", Opcode.serializer().descriptor, isOptional = false)
            element("d", Unit.serializer().descriptor, isOptional = true)
        }

    internal fun CompositeDecoder.decodePayloadAtDataIndex(
        index: Int,
        name: EventName?,
        sequence: Int?,
        opcode: Opcode
    ): Payload = when (opcode) {
        is Opcode.Unknown -> throw SerializationException(
            "Unknown opcode $opcode cannot be deserialized."
        )
        Opcode.VoiceStateUpdate,
        Opcode.Identify,
        Opcode.RequestGuildMembers,
        Opcode.Resume,
        Opcode.StatusUpdate -> with(CommandSerializer) {
            decodeCommandAtDataIndex(descriptor, index, opcode)
        }
        Opcode.Dispatch,
        Opcode.Heartbeat,
        Opcode.HeartbeatACK,
        Opcode.Hello,
        Opcode.InvalidSession,
        Opcode.Reconnect -> with(EventSerializer) {
            decodeEventAtDataIndex(descriptor, index, name, sequence, opcode)
        }
    }

    private fun decodePayloadWithoutData(
        name: EventName?,
        sequence: Int?,
        opcode: Opcode
    ): Payload = when (opcode) {
        Opcode.Dispatch -> {
            requireNotNull(sequence) { "'s' field is required before 'd' field" }
            if (name == EventName.Resumed) Resumed(sequence)
            else throw SerializationException("'d' field is required for Opcode $opcode")
        }
        Opcode.Heartbeat -> Heartbeat(null)
        Opcode.HeartbeatACK -> HeartbeatAck
        Opcode.InvalidSession -> InvalidSession(null)
        Opcode.Reconnect -> Reconnect
        is Opcode.Unknown -> {
            throw SerializationException("Unknown opcode $opcode cannot be deserialized.")
        }
        else -> throw SerializationException("'d' field is required for Opcode $opcode")
    }

    override fun deserialize(decoder: Decoder): Payload = decoder.decodeStructure(descriptor) {
        var name: EventName? = null
        var sequence: Int? = null
        var opcode: Opcode? = null
        var payload: Payload? = null

        while (true) {
            when (val index = decodeElementIndex(descriptor)) {
                0 -> name = decodeNullableSerializableElement(descriptor, index, EventName.serializer().nullable)
                1 -> sequence = decodeNullableSerializableElement(descriptor, index, Int.serializer().nullable)
                2 -> opcode = decodeSerializableElement(descriptor, index, Opcode.serializer())
                3 -> {
                    requireNotNull(opcode) { "'op' field is required before 'd' field" }
                    payload = decodePayloadAtDataIndex(index, name, sequence, opcode)
                }
                CompositeDecoder.DECODE_DONE -> break
            }

        }

        return if (payload != null) payload
        else {
            requireNotNull(opcode) { "'op' field is required before 'd' field" }
            decodePayloadWithoutData(name, sequence, opcode)
        }
    }

    override fun serialize(encoder: Encoder, value: Payload) = when (value) {
        is Event -> Event.serializer().serialize(encoder, value)
        is Command<*> -> Command.genericSerializer().serialize(encoder, value)
    }

}
