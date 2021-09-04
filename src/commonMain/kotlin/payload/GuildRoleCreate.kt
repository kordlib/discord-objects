package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordGuildRole

class GuildRoleCreate(
    override val data: DiscordGuildRole,
    override val sequence: Int
): DispatchEvent<DiscordGuildRole>() {
    override val name: EventName get() = EventName.GuildRoleCreate

}
