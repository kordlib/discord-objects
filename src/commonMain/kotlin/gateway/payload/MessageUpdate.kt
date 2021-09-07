package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordPartialMessage

data class MessageUpdate(
    override val data: DiscordPartialMessage,
    override val sequence: Int
) : DispatchEvent<DiscordPartialMessage>() {
    override val name: EventName get() = EventName.MessageUpdate

}
