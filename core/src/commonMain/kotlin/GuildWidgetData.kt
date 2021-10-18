package dev.kord.discord.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildWidgetData(
    val enabled: Boolean,
    @SerialName("channel_id")
    val channelId: Snowflake?
)
