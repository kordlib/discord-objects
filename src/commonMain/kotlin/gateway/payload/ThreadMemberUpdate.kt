package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordThreadMember

data class ThreadMemberUpdate(
    override val data: DiscordThreadMember,
    override val sequence: Int
) : DispatchEvent<DiscordThreadMember>() {
    override val name: EventName get() = EventName.ThreadMemberUpdate

}
