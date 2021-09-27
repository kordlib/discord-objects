package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordGuildRole
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildRoleUpdate.Serializer::class)
data class GuildRoleUpdate(
    override val data: DiscordGuildRole,
    override val sequence: Int
): Dispatch<DiscordGuildRole>() {
    override val name: EventName get() = EventName.GuildRoleUpdate

    internal object Serializer : KSerializer<GuildRoleUpdate> by DispatchSerializer(::GuildRoleUpdate)
}
