package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordTyping

class TypingStart(
    override val data: DiscordTyping,
    override val sequence: Int
) : DispatchEvent<DiscordTyping>() {
    override val name: EventName get() = EventName.TypingStart
    
}
