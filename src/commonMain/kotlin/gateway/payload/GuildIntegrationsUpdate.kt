package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordGuildIntegrations

data class GuildIntegrationsUpdate(
    override val data: DiscordGuildIntegrations,
    override val sequence: Int
): DispatchEvent<DiscordGuildIntegrations>() {
    override val name: EventName get() = EventName.GuildIntegrationsUpdate

}
