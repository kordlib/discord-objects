package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordUnavailableGuild

class GuildDelete(
    override val data: DiscordUnavailableGuild,
    override val sequence: Int
) : DispatchEvent<DiscordUnavailableGuild>() {
    override val name: EventName get() = EventName.GuildDelete

}
