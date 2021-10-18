package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = WebhooksUpdate.Serializer::class)
data class WebhooksUpdate(
    override val data: Data,
    override val sequence: Int
) : Dispatch<WebhooksUpdate.Data>() {
    override val name: EventName get() = EventName.WebhooksUpdate

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
        @SerialName("channel_id")
        val channelId: Snowflake,
    )

    internal object Serializer : KSerializer<WebhooksUpdate> by DispatchSerializer(::WebhooksUpdate)
}
