package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordUnavailableGuild

data class GuildDelete(
    override val data: DiscordUnavailableGuild,
    override val sequence: Int
) : DispatchEvent<DiscordUnavailableGuild>() {
    override val name: EventName get() = EventName.GuildDelete

}
