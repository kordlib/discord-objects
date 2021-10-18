package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(InteractionResponseType.Serializer::class)
sealed class InteractionResponseType(val type: Int) {
    object Pong : InteractionResponseType(1)
    object ChannelMessageWithSource : InteractionResponseType(4)
    object DeferredChannelMessageWithSource : InteractionResponseType(5)
    object DeferredUpdateMessage : InteractionResponseType(6)
    object UpdateMessage : InteractionResponseType(7)
    class Unknown(type: Int) : InteractionResponseType(type)

    companion object;

    internal object Serializer : KSerializer<InteractionResponseType> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("InteractionResponseType", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): InteractionResponseType {
            return when (val type = decoder.decodeInt()) {
                1 -> Pong
                4 -> ChannelMessageWithSource
                5 -> DeferredChannelMessageWithSource
                6 -> DeferredUpdateMessage
                7 -> UpdateMessage
                else -> Unknown(type)
            }
        }

        override fun serialize(encoder: Encoder, value: InteractionResponseType) {
            encoder.encodeInt(value.type)
        }
    }
}
