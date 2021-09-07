package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordGuild

data class GuildUpdate(
    override val data: DiscordGuild,
    override val sequence: Int
) : DispatchEvent<DiscordGuild>() {
    override val name: EventName get() = EventName.GuildUpdate

}
