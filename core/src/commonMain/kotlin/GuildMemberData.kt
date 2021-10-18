package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

abstract class GuildMemberDataBase {
    abstract val user: Optional<UserData>
    abstract val nick: Optional<String?>
    abstract val roles: List<Snowflake>
    @SerialName("joined_at")
    abstract val joinedAt: String
    @SerialName("premium_since")
    abstract val premiumSince: Optional<String?>
    abstract val deaf: OptionalBoolean
    abstract val mute: OptionalBoolean
    abstract val pending: OptionalBoolean
}

@Serializable
data class GuildMemberData(
    override val user: Optional<UserData> = Optional.Missing(),
    override val nick: Optional<String?> = Optional.Missing(),
    override val roles: List<Snowflake>,
    @SerialName("joined_at")
    override val joinedAt: String,
    @SerialName("premium_since")
    override val premiumSince: Optional<String?> = Optional.Missing(),
    override val deaf: OptionalBoolean = OptionalBoolean.Missing,
    override val mute: OptionalBoolean = OptionalBoolean.Missing,
    override val pending: OptionalBoolean = OptionalBoolean.Missing
) : GuildMemberDataBase()
