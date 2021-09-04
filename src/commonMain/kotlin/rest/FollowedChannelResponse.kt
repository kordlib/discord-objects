package dev.kord.discord.objects.rest

import dev.kord.common.entity.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowedChannelResponse(
    @SerialName("channel_id")
    val channelId: Snowflake,
    @SerialName("webhook_id")
    val webhookId: Snowflake
)
