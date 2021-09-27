package payload.json

import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.EventName
import dev.kord.discord.objects.gateway.payload.UnknownEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class UnknownJsonEvent(
    @SerialName("op")
    override val opcode: Opcode,
    @SerialName("d")
    override val data: JsonElement?,
    @SerialName("t")
    override val name: EventName,
    @SerialName("s")
    override val sequence: Int
) : UnknownEvent
