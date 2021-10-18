package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.GuildMemberDataBase
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.UserData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = GuildMemberAdd.Serializer::class)
data class GuildMemberAdd(
    override val data: Data,
    override val sequence: Int
) : Dispatch<GuildMemberAdd.Data>() {
    override val name: EventName get() = EventName.GuildMemberAdd

    @Serializable
    data class Data(
        override val user: Optional<UserData> = Optional.Missing(),
        override val nick: Optional<String?> = Optional.Missing(),
        override val roles: List<Snowflake>,
        @SerialName("joined_at")
        override val joinedAt: String,
        @SerialName("premium_since")
        override val premiumSince: Optional<String?> = Optional.Missing(),
        override val deaf: OptionalBoolean = OptionalBoolean.Missing,
        override val mute: OptionalBoolean = OptionalBoolean.Missing,
        override val pending: OptionalBoolean = OptionalBoolean.Missing,
        @SerialName("guild_id")
        val guildId: Snowflake
    ) : GuildMemberDataBase()


    internal object Serializer : KSerializer<GuildMemberAdd> by DispatchSerializer(::GuildMemberAdd)
}
