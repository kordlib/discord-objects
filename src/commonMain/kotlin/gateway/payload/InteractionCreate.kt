package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordInteraction

data class InteractionCreate(
    override val data: DiscordInteraction,
    override val sequence: Int
) : DispatchEvent<DiscordInteraction>() {
    override val name: EventName get() = EventName.InteractionCreate

}
