package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.EmojiData
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MessageReactionRemove.Serializer::class)
data class MessageReactionRemove(
    override val data: Data,
    override val sequence: Int,
) : Dispatch<MessageReactionRemove.Data>() {
    override val name: EventName get() = EventName.MessageReactionRemove

    @Serializable
    data class Data(
        @SerialName("user_id")
        val userId: Snowflake,
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("message_id")
        val messageId: Snowflake,
        @SerialName("guild_id")
        val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
        val emoji: EmojiData,
    )

    internal object Serializer : KSerializer<MessageReactionRemove> by DispatchSerializer(::MessageReactionRemove)
}
