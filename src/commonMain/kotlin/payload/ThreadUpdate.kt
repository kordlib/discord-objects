package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordChannel

class ThreadUpdate(
    override val data: DiscordChannel,
    override val sequence: Int
) : DispatchEvent<DiscordChannel>() {
    override val name: EventName get() = EventName.ThreadUpdate

}
