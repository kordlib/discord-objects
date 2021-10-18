package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.Serializable

@Serializable
data class BotActivityData(
    val name: String,
    val type: ActivityType,
    val url: Optional<String?> = Optional.Missing()
)
