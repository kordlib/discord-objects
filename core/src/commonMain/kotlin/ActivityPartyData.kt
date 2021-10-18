package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.Serializable

@Serializable
data class ActivityPartyData(
    val id: Optional<String> = Optional.Missing(),
    val size: Optional<ActivityPartySizeData> = Optional.Missing()
)
