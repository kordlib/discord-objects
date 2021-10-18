package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.EmojiData
import dev.kord.discord.objects.GuildMemberData
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MessageReactionAdd.Serializer::class)
data class MessageReactionAdd(
    override val data: Data,
    override val sequence: Int,
) : Dispatch<MessageReactionAdd.Data>() {
    override val name: EventName get() = EventName.MessageReactionAdd

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
        val member: Optional<GuildMemberData> = Optional.Missing(),
        val emoji: EmojiData,
    )

    internal object Serializer : KSerializer<MessageReactionAdd> by DispatchSerializer(::MessageReactionAdd)
}
