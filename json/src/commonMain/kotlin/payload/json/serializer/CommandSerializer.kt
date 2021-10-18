package dev.kord.discord.objects.payload.json.serializer

import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.*

internal object CommandSerializer : JsonContentPolymorphicSerializer<Command<*>>(Command::class) {

    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Command<*>> {
        val json = element.jsonObject
        val opCodeValue = json["op"]?.jsonPrimitive?.int ?: throw SerializationException(
            "command is expected to contain an opcode but was null"
        )

        val opcode = Opcode.values.firstOrNull { it.value == opCodeValue }
        return when (opcode) {
            Opcode.Heartbeat -> Heartbeat.serializer()
            Opcode.Identify -> Identify.serializer()
            Opcode.StatusUpdate -> UpdatePresence.serializer()
            Opcode.VoiceStateUpdate -> UpdateVoiceState.serializer()
            Opcode.Resume -> Resume.serializer()
            else -> throw SerializationException(
                "opcode $opcode is not a command opcode"
            )
        }

    }

}
