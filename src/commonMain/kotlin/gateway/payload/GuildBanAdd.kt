package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordGuildBan

class GuildBanAdd(
    override val data: DiscordGuildBan,
    override val sequence: Int
) : DispatchEvent<DiscordGuildBan>() {
    override val name: EventName get() = EventName.GuildBanAdd

}
