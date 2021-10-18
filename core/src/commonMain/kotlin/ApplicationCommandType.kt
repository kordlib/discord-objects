package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ApplicationCommandType.Serializer::class)
sealed class ApplicationCommandType(val value: Int) {
    /** The default code for unknown values. */
    class Unknown(value: Int) : ApplicationCommandType(value)
    object ChatInput : ApplicationCommandType(1)
    object User : ApplicationCommandType(2)
    object Message : ApplicationCommandType(3)
    companion object;

    internal object Serializer : KSerializer<ApplicationCommandType> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("type", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): ApplicationCommandType = when (val code = decoder.decodeInt()) {
            1 -> ChatInput
            2 -> User
            3 -> Message
            else -> Unknown(code)
        }

        override fun serialize(encoder: Encoder, value: ApplicationCommandType) = encoder.encodeInt(value.value)
    }

}
