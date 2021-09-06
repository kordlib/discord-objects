package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordChannel

class ThreadCreate(
    override val data: DiscordChannel,
    override val sequence: Int
) : DispatchEvent<DiscordChannel>() {
    override val name: EventName get() = EventName.ThreadCreate

}
