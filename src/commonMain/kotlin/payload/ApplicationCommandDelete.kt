package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordApplicationCommand

data class ApplicationCommandDelete(
    override val data: DiscordApplicationCommand,
    override val sequence: Int,
) : DispatchEvent<DiscordApplicationCommand>() {
    override val name: EventName get() = EventName.ApplicationCommandDelete

}
