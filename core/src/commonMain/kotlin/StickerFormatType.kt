package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = StickerFormatType.Serializer::class)
sealed class StickerFormatType(val value: Int) {
    class Unknown(value: Int) : StickerFormatType(value)
    object PNG : StickerFormatType(1)
    object APNG : StickerFormatType(2)
    object LOTTIE : StickerFormatType(3)

    companion object {
        val values: Set<StickerFormatType> = setOf(PNG, APNG, LOTTIE)
    }

    internal object Serializer : KSerializer<StickerFormatType> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("Kord.MessageStickerType", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): StickerFormatType = when (val value = decoder.decodeInt()) {
            1 -> PNG
            2 -> APNG
            3 -> LOTTIE
            else -> Unknown(value)
        }

        override fun serialize(encoder: Encoder, value: StickerFormatType) {
            encoder.encodeInt(value.value)
        }
    }
}
