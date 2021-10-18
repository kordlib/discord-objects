package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.GuildMemberData
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = TypingStart.Serializer::class)
data class TypingStart(
    override val data: Data,
    override val sequence: Int
) : Dispatch<TypingStart.Data>() {
    override val name: EventName get() = EventName.TypingStart

    @Serializable
    data class Data(
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("guild_id")
        val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
        @SerialName("user_id")
        val userId: Snowflake,
        val timestamp: Long,
        val member: Optional<GuildMemberData> = Optional.Missing()
    )


    internal object Serializer : KSerializer<TypingStart> by DispatchSerializer(::TypingStart)
}
