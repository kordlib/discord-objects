package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ActivityFlags.Serializer::class)
class ActivityFlags(val value: Int) {

    val flags: Set<ActivityFlag> get() = ActivityFlag.values().filter { (it.value and value) == it.value }.toSet()

    operator fun contains(flag: ActivityFlag): Boolean = (flag.value and value) == flag.value

    internal object Serializer : KSerializer<ActivityFlags> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("Kord.ActivityFlags", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): ActivityFlags = ActivityFlags(decoder.decodeInt())

        override fun serialize(encoder: Encoder, value: ActivityFlags) {
            encoder.encodeInt(value.value)
        }
    }
}
