package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordAddedGuildMember

class GuildMemberAdd(
    override val data: DiscordAddedGuildMember,
    override val sequence: Int
): DispatchEvent<DiscordAddedGuildMember>() {
    override val name: EventName get() = EventName.GuildMemberAdd

}
