package dev.kord.discord.objects.payload

import dev.kord.discord.objects.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class MessageReactionRemoveEmoji(
    override val data: Data,
    override val sequence: Int
) : DispatchEvent<MessageReactionRemoveEmoji.Data>() {
    override val name: EventName get() = EventName.MessageReactionRemoveEmoji


    @Serializable
    data class Data(
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("guild_id")
        val guildId: Snowflake,
        @SerialName("message_id")
        val messageId: Snowflake,
        val emoji: DiscordRemovedReactionEmoji,
    )

}

@Serializable
data class DiscordRemovedReactionEmoji(
    val id: Snowflake?,
    val name: String?,
)
