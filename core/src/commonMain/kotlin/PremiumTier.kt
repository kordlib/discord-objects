package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * A representation of a [Discord Premium tier](https://discord.com/developers/docs/resources/guild#guild-object-premium-tier).
 */
@Serializable(with = PremiumTier.Serializer::class)
sealed class PremiumTier(val value: Int) {

    override fun equals(other: Any?): Boolean {
        if (other !is PremiumTier) return false
        return other.value == value
    }

    override fun hashCode(): Int = value.hashCode()

    override fun toString(): String = this::class.simpleName!!


    class Unknown(value: Int) : PremiumTier(value) {
        override fun toString(): String = "Unknown(value=$value)"
    }

    object None : PremiumTier(0)
    object One : PremiumTier(1)
    object Two : PremiumTier(2)
    object Three : PremiumTier(3)

    internal object Serializer : KSerializer<PremiumTier> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("Kord.PremiumTier", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): PremiumTier = when (val value = decoder.decodeInt()) {
            0 -> None
            1 -> One
            2 -> Two
            3 -> Three
            else -> Unknown(value)
        }

        override fun serialize(encoder: Encoder, value: PremiumTier) {
            encoder.encodeInt(value.value)
        }

    }
}
