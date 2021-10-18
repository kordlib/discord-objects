package dev.kord.discord.objects

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.listSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(ActivityPartySizeData.Serializer::class)
data class ActivityPartySizeData(
    val current: Int,
    val maximum: Int
) {
    internal object Serializer : KSerializer<ActivityPartySizeData> {
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor
            get() = listSerialDescriptor(Int.serializer().descriptor)

        private val delegate = ListSerializer(Int.serializer())

        override fun deserialize(decoder: Decoder): ActivityPartySizeData {
            val (current, maximum) = delegate.deserialize(decoder)
            return ActivityPartySizeData(current, maximum)
        }

        override fun serialize(encoder: Encoder, value: ActivityPartySizeData) {
            delegate.serialize(encoder, listOf(value.current, value.maximum))
        }
    }
}
