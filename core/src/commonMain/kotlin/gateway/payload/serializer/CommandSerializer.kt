package dev.kord.discord.objects.gateway.payload.serializer

import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.*
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

internal class CommandSerializer<T>(@Suppress("UNUSED_PARAMETER") ignored: KSerializer<T>) : KSerializer<Command<T>> {

    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("dev.kord.discord.objects.gateway.payload.Command") {
            element("t", EventName.serializer().descriptor, isOptional = true)
            element("s", Int.serializer().descriptor, isOptional = true)
            element("op", Opcode.serializer().descriptor, isOptional = false)
            element("d", Unit.serializer().descriptor, isOptional = true)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): Command<T> = decoder.decodeStructure(descriptor) {
        var opcode: Opcode? = null

        var command: Command<*>? = null

        while (true) {
            when (val index = decodeElementIndex(descriptor)) {
                0 -> decodeNullableSerializableElement(descriptor, index, Unit.serializer().nullable)
                1 -> decodeNullableSerializableElement(descriptor, index, Unit.serializer().nullable)
                2 -> opcode = decodeSerializableElement(descriptor, index, Opcode.serializer())
                3 -> {
                    requireNotNull(opcode) { "'op' field is required before 'd' field" }
                    command = decodeCommandAtDataIndex(descriptor, index, opcode)
                }
                CompositeDecoder.DECODE_DONE -> break
            }
        }

        @Suppress("UNCHECKED_CAST")
        return command as? Command<T> ?: throw SerializationException("'d' field is required for Command Payloads")
    }

    override fun serialize(encoder: Encoder, value: Command<T>) = when (value) {
        is Heartbeat -> Heartbeat.serializer().serialize(encoder, value)
        is Identify -> Identify.serializer().serialize(encoder, value)
        is RequestGuildMembers -> RequestGuildMembers.serializer().serialize(encoder, value)
        is Resume -> Resume.serializer().serialize(encoder, value)
        is UpdatePresence -> UpdatePresence.serializer().serialize(encoder, value)
        is UpdateVoiceState -> UpdateVoiceState.serializer().serialize(encoder, value)
    }

    companion object {
        internal fun CompositeDecoder.decodeCommandAtDataIndex(
            descriptor: SerialDescriptor,
            index: Int,
            opcode: Opcode
        ): Command<*> = when (opcode) {
            Opcode.Dispatch,
            Opcode.Heartbeat,
            Opcode.HeartbeatACK,
            Opcode.Hello,
            Opcode.InvalidSession,
            Opcode.Reconnect,
            Opcode.VoiceStateUpdate -> throw SerializationException("Opcode $opcode is not a command opcode.")
            Opcode.Identify -> Identify(decodeSerializableElement(descriptor, index, Identify.Data.serializer()))
            Opcode.RequestGuildMembers -> RequestGuildMembers(decodeSerializableElement(descriptor, index, RequestGuildMembers.Data.serializer()))
            Opcode.Resume -> Resume(decodeSerializableElement(descriptor, index, Resume.Data.serializer()))
            Opcode.StatusUpdate -> UpdatePresence(decodeSerializableElement(descriptor, index, UpdatePresence.Data.serializer()))
            is Opcode.Unknown -> throw SerializationException("Unknown Opcode $opcode cannot be deserialized")
        }
    }

}
