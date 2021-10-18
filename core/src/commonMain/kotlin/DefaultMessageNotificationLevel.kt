package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = DefaultMessageNotificationLevel.Serializer::class)
sealed class DefaultMessageNotificationLevel(val value: Int) {

    override fun equals(other: Any?): Boolean {
        if (other !is DefaultMessageNotificationLevel) return false

        return other.value == value
    }

    override fun hashCode(): Int = value.hashCode()

    override fun toString(): String = this::class.simpleName!!

    class Unknown(value: Int) : DefaultMessageNotificationLevel(value) {
        override fun toString(): String = "Unknown(value=$value)"
    }

    object AllMessages : DefaultMessageNotificationLevel(0)
    object OnlyMentions : DefaultMessageNotificationLevel(1)

    internal object Serializer : KSerializer<DefaultMessageNotificationLevel> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("default_message_notifications", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): DefaultMessageNotificationLevel =
            when (val value = decoder.decodeInt()) {
                0 -> AllMessages
                1 -> OnlyMentions
                else -> Unknown(value)
            }

        override fun serialize(encoder: Encoder, value: DefaultMessageNotificationLevel) {
            encoder.encodeInt(value.value)
        }
    }

}
