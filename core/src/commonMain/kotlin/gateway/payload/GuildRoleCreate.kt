package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.RoleData
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = GuildRoleCreate.Serializer::class)
data class GuildRoleCreate(
    override val data: Data,
    override val sequence: Int
) : Dispatch<GuildRoleCreate.Data>() {
    override val name: EventName get() = EventName.GuildRoleCreate

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
        val role: RoleData,
    )

    internal object Serializer : KSerializer<GuildRoleCreate> by DispatchSerializer(::GuildRoleCreate)
}
