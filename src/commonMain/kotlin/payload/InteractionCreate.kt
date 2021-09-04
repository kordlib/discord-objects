package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordInteraction

class InteractionCreate(
    override val data: DiscordInteraction,
    override val sequence: Int
) : DispatchEvent<DiscordInteraction>() {
    override val name: EventName get() = EventName.InteractionCreate

}
