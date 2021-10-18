package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = IntegrationExpireBehavior.Serializer::class)
sealed class IntegrationExpireBehavior(val value: Int) {
    class Unknown(value: Int) : IntegrationExpireBehavior(value)
    object RemoveRole : IntegrationExpireBehavior(0)
    object Kick : IntegrationExpireBehavior(1)

    companion object Serializer : KSerializer<IntegrationExpireBehavior> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("expire_behavior", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): IntegrationExpireBehavior = when (val value = decoder.decodeInt()) {
            0 -> RemoveRole
            1 -> Kick
            else -> Unknown(value)
        }

        override fun serialize(encoder: Encoder, value: IntegrationExpireBehavior) {
            encoder.encodeInt(value.value)
        }

    }
}
