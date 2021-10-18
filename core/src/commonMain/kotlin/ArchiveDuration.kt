package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ArchiveDuration.Serializer::class)
sealed class ArchiveDuration(val value: Int) {

    override fun equals(other: Any?): Boolean {
        if (other !is ArchiveDuration) return false
        return other.value == value
    }

    override fun hashCode(): Int = value.hashCode()

    override fun toString(): String = this::class.simpleName!!

    class Unknown(value: Int) : ArchiveDuration(value) {
        override fun toString(): String = "Unknown(value=$value)"
    }
    object Hour : ArchiveDuration(60)
    object Day : ArchiveDuration(1440)
    object ThreeDays : ArchiveDuration(4320)
    object Week : ArchiveDuration(10080)

    object Serializer : KSerializer<ArchiveDuration> {
        override fun deserialize(decoder: Decoder): ArchiveDuration {
            val value = decoder.decodeInt()
            return values.firstOrNull { it.value == value } ?: Unknown(value)
        }

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("AutoArchieveDuration", PrimitiveKind.INT)

        override fun serialize(encoder: Encoder, value: ArchiveDuration) {
            encoder.encodeInt(value.value)
        }
    }

    companion object {
        val values: Set<ArchiveDuration>
            get() = setOf(
                Hour,
                Day,
                ThreeDays,
                Week,
            )
    }
}
