package dev.kord.discord.objects.rest

import dev.kord.discord.objects.DiscordUser
import kotlinx.serialization.Serializable

@Serializable
data class BanResponse(
    val reason: String?,
    val user: DiscordUser
)
