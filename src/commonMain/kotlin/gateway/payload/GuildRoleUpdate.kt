package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordGuildRole

class GuildRoleUpdate(
    override val data: DiscordGuildRole,
    override val sequence: Int
): DispatchEvent<DiscordGuildRole>() {
    override val name: EventName get() = EventName.GuildRoleUpdate

}
