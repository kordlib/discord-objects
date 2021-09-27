package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordDeletedGuildRole
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildRoleDelete.Serializer::class)
data class GuildRoleDelete(
    override val data: DiscordDeletedGuildRole,
    override val sequence: Int
): Dispatch<DiscordDeletedGuildRole>() {
    override val name: EventName get() = EventName.GuildRoleDelete

    internal object Serializer : KSerializer<GuildRoleDelete> by DispatchSerializer(::GuildRoleDelete)
}
