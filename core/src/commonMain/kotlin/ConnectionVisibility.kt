package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ConnectionVisibility.Serializer::class)
sealed class ConnectionVisibility(val value: Int) {
    class Unknown(value: Int) : ConnectionVisibility(value)

    /**
     * The connection is invisible to everyone except the user themselves.
     */
    object None : ConnectionVisibility(0)

    /**
     * The connection is visible to everyone.
     */
    object Everyone : ConnectionVisibility(1)

    internal object Serializer : KSerializer<ConnectionVisibility> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("Kord.DiscordConnectionVisibility", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): ConnectionVisibility =
            when (val value = decoder.decodeInt()) {
                0 -> None
                1 -> Everyone
                else -> Unknown(value)
            }

        override fun serialize(encoder: Encoder, value: ConnectionVisibility) {
            encoder.encodeInt(value.value)
        }
    }

}
