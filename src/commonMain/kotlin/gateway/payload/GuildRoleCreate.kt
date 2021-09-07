package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordGuildRole

data class GuildRoleCreate(
    override val data: DiscordGuildRole,
    override val sequence: Int
): DispatchEvent<DiscordGuildRole>() {
    override val name: EventName get() = EventName.GuildRoleCreate

}
