package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ExplicitContentFilter.Serializer::class)
sealed class ExplicitContentFilter(val value: Int) {

    override fun equals(other: Any?): Boolean {
        if (other !is ExplicitContentFilter) return false
        return other.value == value
    }

    override fun hashCode(): Int = value.hashCode()

    class Unknown(value: Int) : ExplicitContentFilter(value)
    object Disabled : ExplicitContentFilter(0)
    object MembersWithoutRoles : ExplicitContentFilter(1)
    object AllMembers : ExplicitContentFilter(2)

    internal object Serializer : KSerializer<ExplicitContentFilter> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("explicit_content_filter", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): ExplicitContentFilter = when (val value = decoder.decodeInt()) {
            0 -> Disabled
            1 -> MembersWithoutRoles
            2 -> AllMembers
            else -> Unknown(value)
        }

        override fun serialize(encoder: Encoder, value: ExplicitContentFilter) {
            encoder.encodeInt(value.value)
        }

    }
}
