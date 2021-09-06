package dev.kord.discord.objects.gateway

import dev.kord.discord.objects.DiscordBotActivity
import dev.kord.discord.objects.PresenceStatus
import kotlinx.serialization.Serializable

@Serializable
class DiscordPresence(
    val status: PresenceStatus,
    val afk: Boolean,
    val since: Long? = null,
    val game: DiscordBotActivity? = null,
)
