package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordMessage

class MessageCreate(
    override val data: DiscordMessage,
    override val sequence: Int
): DispatchEvent<DiscordMessage>() {
    override val name: EventName get() = EventName.MessageCreate

}
