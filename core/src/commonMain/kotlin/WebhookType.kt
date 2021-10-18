package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = WebhookType.Serializer::class)
sealed class WebhookType(val value: Int) {
    class Unknown(value: Int) : WebhookType(value)

    /**
     * Incoming Webhooks can post messages to channels with a generated token.
     */
    object Incoming : WebhookType(1)

    /**
     * 	Channel Follower Webhooks are internal webhooks used with Channel Following to post new messages into channels.
     */
    object ChannelFollower : WebhookType(2)

    internal object Serializer : KSerializer<WebhookType> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("type", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): WebhookType = when (val value = decoder.decodeInt()) {
            1 -> Incoming
            2 -> ChannelFollower
            else -> Unknown(value)
        }

        override fun serialize(encoder: Encoder, value: WebhookType) {
            encoder.encodeInt(value.value)
        }
    }
}
