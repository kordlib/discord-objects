package dev.kord.discord.objects

import dev.kord.discord.objects.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowedChannelData(
    @SerialName("channel_id")
    val channelId: Snowflake,
    @SerialName("webhook_id")
    val webhookId: Snowflake
)
