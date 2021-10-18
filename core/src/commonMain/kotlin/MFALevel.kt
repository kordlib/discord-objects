package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = MFALevel.Serializer::class)
sealed class MFALevel(val value: Int) {

    override fun equals(other: Any?): Boolean {
        if (other !is MFALevel) return false
        return other.value == value
    }

    override fun hashCode(): Int = value.hashCode()


    class Unknown(value: Int) : MFALevel(value)
    object None : MFALevel(0)
    object Elevated : MFALevel(1)

    internal object Serializer : KSerializer<MFALevel> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("Kord.MFALevel", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): MFALevel = when (val value = decoder.decodeInt()) {
            0 -> None
            1 -> Elevated
            else -> Unknown(value)
        }

        override fun serialize(encoder: Encoder, value: MFALevel) {
            encoder.encodeInt(value.value)
        }
    }
}
