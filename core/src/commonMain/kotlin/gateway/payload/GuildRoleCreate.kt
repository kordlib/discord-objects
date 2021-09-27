package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordGuildRole
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildRoleCreate.Serializer::class)
data class GuildRoleCreate(
    override val data: DiscordGuildRole,
    override val sequence: Int
): Dispatch<DiscordGuildRole>() {
    override val name: EventName get() = EventName.GuildRoleCreate

    internal object Serializer : KSerializer<GuildRoleCreate> by DispatchSerializer(::GuildRoleCreate)
}
