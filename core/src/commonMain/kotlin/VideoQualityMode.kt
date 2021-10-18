package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = VideoQualityMode.Serializer::class)
sealed class VideoQualityMode(val value: Int){
    class Unknown(value: Int): VideoQualityMode(value)

    /**
     * Discord chooses the quality for optimal performance.
     */
    object Auto : VideoQualityMode(1)

    /**
     * 720p
     */
    object Full : VideoQualityMode(2)

    companion object {
        val values = setOf(Auto, Full)
    }

    internal object Serializer : KSerializer<VideoQualityMode> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("kord.VideoQualityMode", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): VideoQualityMode {
            val value = decoder.decodeInt()
            return values.firstOrNull { it.value == value } ?: Unknown(value)
        }

        override fun serialize(encoder: Encoder, value: VideoQualityMode) {
            encoder.encodeInt(value.value)
        }
    }

}
