package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordGuildBan

data class GuildBanRemove(
    override val data: DiscordGuildBan,
    override val sequence: Int
) : DispatchEvent<DiscordGuildBan>() {
    override val name: EventName get() = EventName.GuildBanRemove

}
