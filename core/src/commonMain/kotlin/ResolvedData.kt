package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.Serializable

@Serializable
data class ResolvedData(
    val members: Optional<Map<Snowflake, GuildMemberData>> = Optional.Missing(),
    val users: Optional<Map<Snowflake, UserData>> = Optional.Missing(),
    val roles: Optional<Map<Snowflake, RoleData>> = Optional.Missing(),
    val channels: Optional<Map<Snowflake, ChannelData>> = Optional.Missing(),
    val messages: Optional<Map<Snowflake, MessageData>> = Optional.Missing(),
)
