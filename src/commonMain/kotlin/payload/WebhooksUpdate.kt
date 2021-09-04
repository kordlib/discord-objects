package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordWebhooksUpdateData

class WebhooksUpdate(
    override val data: DiscordWebhooksUpdateData,
    override val sequence: Int
) : DispatchEvent<DiscordWebhooksUpdateData>() {
    override val name: EventName get() = EventName.WebhooksUpdate

}
