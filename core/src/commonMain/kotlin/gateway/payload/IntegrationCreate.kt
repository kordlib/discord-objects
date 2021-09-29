package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordIntegration
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = IntegrationCreate.Serializer::class)
data class IntegrationCreate(
    override val data: DiscordIntegration,
    override val sequence: Int
) : Dispatch<DiscordIntegration>() {
    override val name: EventName get() = EventName.IntegrationCreate

    internal object Serializer : KSerializer<IntegrationCreate> by DispatchSerializer(::IntegrationCreate)

}
