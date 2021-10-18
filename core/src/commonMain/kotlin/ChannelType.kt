package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ChannelType.Serializer::class)
sealed class ChannelType(val value: Int) {

    override fun equals(other: Any?): Boolean {
        if (other !is ChannelType) return false
        return other.value == value
    }

    override fun hashCode(): Int = value.hashCode()

    override fun toString(): String = this::class.simpleName!!

    /** The default code for unknown values. */
    class Unknown(value: Int) : ChannelType(value) {
        override fun toString(): String = "Unknown(value=$value)"
    }

    /** A text channel within a server. */
    object GuildText : ChannelType(0)

    /** A direct message between users. */
    object DM : ChannelType(1)

    /** A voice channel within a server. */
    object GuildVoice : ChannelType(2)

    /** A direct message between multiple users. */
    object GroupDM : ChannelType(3)

    /** An organization category. */
    object GuildCategory : ChannelType(4)

    /** A channel that users can follow and crosspost into their own server. */
    object GuildNews : ChannelType(5)

    /** A channel in which game developers can sell their game on Discord. */
    object GuildStore : ChannelType(6)

    object PublicNewsThread : ChannelType(10)

    object PublicGuildThread : ChannelType(11)

    object PrivateThread : ChannelType(12)



    object GuildStageVoice : ChannelType(13)

    companion object;

    internal object Serializer : KSerializer<ChannelType> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("type", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): ChannelType = when (val code = decoder.decodeInt()) {
            0 -> GuildText
            1 -> DM
            2 -> GuildVoice
            3 -> GroupDM
            4 -> GuildCategory
            5 -> GuildNews
            6 -> GuildStore
            10 -> PublicNewsThread
            11 -> PublicGuildThread
            12 -> PrivateThread
            13 -> GuildStageVoice
            else -> Unknown(code)
        }

        override fun serialize(encoder: Encoder, value: ChannelType) = encoder.encodeInt(value.value)
    }

}
