package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = GuildRoleDelete.Serializer::class)
data class GuildRoleDelete(
    override val data: Data,
    override val sequence: Int
) : Dispatch<GuildRoleDelete.Data>() {
    override val name: EventName get() = EventName.GuildRoleDelete

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
        @SerialName("role_id")
        val id: Snowflake,
    )

    internal object Serializer : KSerializer<GuildRoleDelete> by DispatchSerializer(::GuildRoleDelete)
}
