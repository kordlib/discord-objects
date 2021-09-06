package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordRemovedGuildMember

class GuildMemberRemove(
    override val data: DiscordRemovedGuildMember,
    override val sequence: Int
): DispatchEvent<DiscordRemovedGuildMember>() {
    override val name: EventName get() = EventName.GuildMemberRemove

}
