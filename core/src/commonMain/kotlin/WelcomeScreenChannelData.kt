package dev.kord.discord.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WelcomeScreenChannelData(
    @SerialName("channel_id")
    val channelId: Snowflake,
    val description: String,
    @SerialName("emoji_id")
    val emojiId: Snowflake?,
    @SerialName("emoji_name")
    val emojiName: String?
)
