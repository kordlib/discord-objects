package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MessageDelete.Serializer::class)
data class MessageDelete(
    override val data: Data,
    override val sequence: Int
) : Dispatch<MessageDelete.Data>() {
    override val name: EventName get() = EventName.MessageDelete

    @Serializable
    data class Data(
        val id: Snowflake,
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("guild_id")
        val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
    )

    internal object Serializer : KSerializer<MessageDelete> by DispatchSerializer(::MessageDelete)
}
