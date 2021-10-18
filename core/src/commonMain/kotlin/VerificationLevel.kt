package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * A representation of a [Discord Verification Level](https://discord.com/developers/docs/resources/guild#guild-object-verification-level).
 */
@Serializable(with = VerificationLevel.Serializer::class)
sealed class VerificationLevel(val value: Int) {

    override fun equals(other: Any?): Boolean {
        if (other !is VerificationLevel) return false
        return other.value == value
    }

    override fun hashCode(): Int = value.hashCode()

    override fun toString(): String = this::class.simpleName!!

    class Unknown(value: Int) : VerificationLevel(value) {
        override fun toString(): String = "Unknown(value=$value)"
    }

    /** Unrestricted. */
    object None : VerificationLevel(0)

    /** Must have verified email and account.  */
    object Low : VerificationLevel(1)

    /** Must be registered on Discord for longer than 5 minutes. */
    object Medium : VerificationLevel(2)

    /** Must be member of the server for longer than 10 minutes */
    object High : VerificationLevel(3)

    /** Must have a verified phone number */
    object VeryHigh : VerificationLevel(4)

    internal object Serializer : KSerializer<VerificationLevel> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("Kord.VerificationLevel", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): VerificationLevel = when (val value = decoder.decodeInt()) {
            0 -> None
            1 -> Low
            2 -> Medium
            3 -> High
            4 -> VeryHigh
            else -> Unknown(value)
        }

        override fun serialize(encoder: Encoder, value: VerificationLevel) {
            encoder.encodeInt(value.value)
        }

    }
}
