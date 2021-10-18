package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.RoleData
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = GuildRoleUpdate.Serializer::class)
data class GuildRoleUpdate(
    override val data: Data,
    override val sequence: Int
) : Dispatch<GuildRoleUpdate.Data>() {
    override val name: EventName get() = EventName.GuildRoleUpdate

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
        val role: RoleData,
    )

    internal object Serializer : KSerializer<GuildRoleUpdate> by DispatchSerializer(::GuildRoleUpdate)
}
