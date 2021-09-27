package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordWebhooksUpdateData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = WebhooksUpdate.Serializer::class)
data class WebhooksUpdate(
    override val data: DiscordWebhooksUpdateData,
    override val sequence: Int
) : Dispatch<DiscordWebhooksUpdateData>() {
    override val name: EventName get() = EventName.WebhooksUpdate

    internal object Serializer : KSerializer<WebhooksUpdate> by DispatchSerializer(::WebhooksUpdate)
}
