package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordThreadMember

class ThreadMemberUpdate(
    override val data: DiscordThreadMember,
    override val sequence: Int
) : DispatchEvent<DiscordThreadMember>() {
    override val name: EventName get() = EventName.ThreadMemberUpdate

}
