package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordDeletedGuildRole

class GuildRoleDelete(
    override val data: DiscordDeletedGuildRole,
    override val sequence: Int
): DispatchEvent<DiscordDeletedGuildRole>() {
    override val name: EventName get() = EventName.GuildRoleDelete

}
