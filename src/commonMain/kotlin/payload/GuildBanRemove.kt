package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordGuildBan

class GuildBanRemove(
    override val data: DiscordGuildBan,
    override val sequence: Int
) : DispatchEvent<DiscordGuildBan>() {
    override val name: EventName get() = EventName.GuildBanRemove

}
