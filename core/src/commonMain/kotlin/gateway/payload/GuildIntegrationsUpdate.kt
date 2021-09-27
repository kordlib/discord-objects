package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordGuildIntegrations
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildIntegrationsUpdate.Serializer::class)
data class GuildIntegrationsUpdate(
    override val data: DiscordGuildIntegrations,
    override val sequence: Int
): Dispatch<DiscordGuildIntegrations>() {
    override val name: EventName get() = EventName.GuildIntegrationsUpdate

    internal object Serializer : KSerializer<GuildIntegrationsUpdate> by DispatchSerializer(::GuildIntegrationsUpdate)
}
