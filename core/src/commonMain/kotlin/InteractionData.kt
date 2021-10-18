package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InteractionData(
    val id: Snowflake,
    @SerialName("application_id")
    val applicationId: Snowflake,
    val type: InteractionType,
    val data: Optional<InteractionDataData> = Optional.Missing(),
    @SerialName("guild_id")
    val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
    @SerialName("channel_id")
    val channelId: OptionalSnowflake = OptionalSnowflake.Missing,
    val member: Optional<InteractionGuildMemberData> = Optional.Missing(),
    val user: Optional<UserData> = Optional.Missing(),
    val token: String,
    val version: Int,
    val message: Optional<MessageData> = Optional.Missing(),
)
