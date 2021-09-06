package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordApplicationCommand

data class ApplicationCommandCreate(
    override val data: DiscordApplicationCommand,
    override val sequence: Int
) : DispatchEvent<DiscordApplicationCommand>() {
    override val name: EventName get() = EventName.ApplicationCommandCreate

}
