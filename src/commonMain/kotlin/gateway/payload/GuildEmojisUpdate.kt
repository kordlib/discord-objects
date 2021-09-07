package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordUpdatedEmojis

data class GuildEmojisUpdate(
    override val data: DiscordUpdatedEmojis,
    override val sequence: Int
) : DispatchEvent<DiscordUpdatedEmojis>() {

    override val name: EventName get() = EventName.GuildEmojisUpdate

}
