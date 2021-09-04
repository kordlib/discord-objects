package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordGuild

class GuildUpdate(
    override val data: DiscordGuild,
    override val sequence: Int
) : DispatchEvent<DiscordGuild>() {
    override val name: EventName get() = EventName.GuildUpdate

}
