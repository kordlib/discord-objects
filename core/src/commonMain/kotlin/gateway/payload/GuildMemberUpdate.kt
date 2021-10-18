package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.UserData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = GuildMemberUpdate.Serializer::class)
data class GuildMemberUpdate(
    override val data: Data,
    override val sequence: Int
) : Dispatch<GuildMemberUpdate.Data>() {
    override val name: EventName get() = EventName.GuildMemberUpdate

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
        val roles: List<Snowflake>,
        val user: UserData,
        val nick: Optional<String?> = Optional.Missing(),
        @SerialName("joined_at")
        val joinedAt: String,
        @SerialName("premium_since")
        val premiumSince: Optional<String?> = Optional.Missing(),
        val deaf: OptionalBoolean = OptionalBoolean.Missing,
        val mute: OptionalBoolean = OptionalBoolean.Missing,
        val pending: OptionalBoolean = OptionalBoolean.Missing
    )

    internal object Serializer : KSerializer<GuildMemberUpdate> by DispatchSerializer(::GuildMemberUpdate)
}
