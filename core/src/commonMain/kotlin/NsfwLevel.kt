package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * A representation of a [Discord Guild NSFW Level](https://discord.com/developers/docs/resources/guild#guild-object-guild-nsfw-level).
 */
@Serializable(with = NsfwLevel.Serializer::class)
sealed class NsfwLevel(val value: Int) {

    override fun equals(other: Any?): Boolean {
        if (other !is NsfwLevel) return false
        return other.value == value
    }

    override fun hashCode(): Int = value.hashCode()

    override fun toString(): String = this::class.simpleName!!


    class Unknown(value: Int) : NsfwLevel(value) {
        override fun toString(): String = "Unknown(value=$value)"
    }

    object Default : NsfwLevel(0)

    object Explicit : NsfwLevel(1)

    object Safe : NsfwLevel(2)

    object AgeRestricted : NsfwLevel(3)

    internal object Serializer : KSerializer<NsfwLevel> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("Kord.GuildNsfwLevel", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): NsfwLevel = when (val value = decoder.decodeInt()) {
            0 -> Default
            1 -> Explicit
            2 -> Safe
            3 -> AgeRestricted
            else -> Unknown(value)
        }

        override fun serialize(encoder: Encoder, value: NsfwLevel) {
            encoder.encodeInt(value.value)
        }

    }
}
