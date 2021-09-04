package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordPartialMessage

class MessageUpdate(
    override val data: DiscordPartialMessage,
    override val sequence: Int
) : DispatchEvent<DiscordPartialMessage>() {
    override val name: EventName get() = EventName.MessageUpdate

}
