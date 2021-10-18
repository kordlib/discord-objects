package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MessageReactionRemoveEmoji.Serializer::class)
data class MessageReactionRemoveEmoji(
    override val data: Data,
    override val sequence: Int
) : Dispatch<MessageReactionRemoveEmoji.Data>() {
    override val name: EventName get() = EventName.MessageReactionRemoveEmoji

    @Serializable
    data class Data(
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("guild_id")
        val guildId: Snowflake,
        @SerialName("message_id")
        val messageId: Snowflake,
        val emoji: Emoji,
    )

    @Serializable
    data class Emoji(
        val id: Snowflake?,
        val name: String?,
    )

    internal object Serializer : KSerializer<MessageReactionRemoveEmoji> by DispatchSerializer(::MessageReactionRemoveEmoji)
}
