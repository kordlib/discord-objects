package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MessageReactionRemoveAll.Serializer::class)
data class MessageReactionRemoveAll(
    override val data: Data,
    override val sequence: Int
) : Dispatch<MessageReactionRemoveAll.Data>() {
    override val name: EventName get() = EventName.MessageReactionRemoveAll

    @Serializable
    data class Data(
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("message_id")
        val messageId: Snowflake,
        @SerialName("guild_id")
        val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
    )


    internal object Serializer : KSerializer<MessageReactionRemoveAll> by DispatchSerializer(::MessageReactionRemoveAll)
}
