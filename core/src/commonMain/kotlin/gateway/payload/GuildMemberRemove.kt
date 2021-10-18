package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.UserData
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = GuildMemberRemove.Serializer::class)
data class GuildMemberRemove(
    override val data: Data,
    override val sequence: Int
) : Dispatch<GuildMemberRemove.Data>() {
    override val name: EventName get() = EventName.GuildMemberRemove

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
        val user: UserData
    )

    internal object Serializer : KSerializer<GuildMemberRemove> by DispatchSerializer(::GuildMemberRemove)
}
