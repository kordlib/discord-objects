package dev.kord.discord.objects

import dev.kord.discord.objects.optional.OptionalLong
import kotlinx.serialization.Serializable

@Serializable
data class ActivityTimeStampsData(
    val start: OptionalLong = OptionalLong.Missing,
    val end: OptionalLong = OptionalLong.Missing
)
