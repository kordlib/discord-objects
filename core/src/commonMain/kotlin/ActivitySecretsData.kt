package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.Serializable

@Serializable
data class ActivitySecretsData(
    val join: Optional<String> = Optional.Missing(),
    val spectate: Optional<String> = Optional.Missing(),
    val match: Optional<String> = Optional.Missing()
)
