package dev.kord.discord.objects.payload.json.serializer

import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.*
import payload.json.UnknownJsonEvent

internal object PayloadSerializer : JsonContentPolymorphicSerializer<Payload>(Payload::class) {

    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Payload> {
        val json = element.jsonObject
        val opCodeValue = json["op"]?.jsonPrimitive?.int ?: throw SerializationException(
            "command is expected to contain an opcode but was null"
        )
        val opcode = Opcode.values.firstOrNull { it.value == opCodeValue }
        return when (opcode) {
            null, is Opcode.Unknown -> UnknownJsonEvent.serializer()
            Opcode.Dispatch -> DispatchSerializer
            Opcode.Heartbeat -> Heartbeat.serializer()
            Opcode.Identify -> Identify.serializer()
            Opcode.StatusUpdate -> UpdatePresence.serializer()
            Opcode.VoiceStateUpdate -> UpdateVoiceState.serializer()
            Opcode.Resume -> Resume.serializer()
            Opcode.Reconnect -> Reconnect.serializer()
            Opcode.RequestGuildMembers -> RequestGuildMembers.serializer()
            Opcode.InvalidSession -> InvalidSession.serializer()
            Opcode.Hello -> Hello.serializer()
            Opcode.HeartbeatACK -> HeartbeatAck.serializer()
        }
    }
}
