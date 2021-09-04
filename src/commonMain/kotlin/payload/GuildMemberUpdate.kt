package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordUpdatedGuildMember

class GuildMemberUpdate(
    override val data: DiscordUpdatedGuildMember,
    override val sequence: Int
) : DispatchEvent<DiscordUpdatedGuildMember>() {
    override val name: EventName get() = EventName.GuildMemberUpdate

}
