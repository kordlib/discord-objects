package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordBotActivity
import dev.kord.discord.objects.PresenceStatus
import dev.kord.discord.objects.gateway.Opcode
import kotlinx.serialization.Serializable

@Serializable
data class StatusUpdate(
    val since: Long?,
    val activities: List<DiscordBotActivity>,
    val status: PresenceStatus,
    val afk: Boolean,
) : Command {

    override val opcode: Opcode
        get() = Opcode.StatusUpdate

}
