package dev.kord.discord.objects

import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * An instance of a [Discord shard](https://discord.com/developers/docs/topics/gateway#sharding).
 */
@Serializable(with = ShardData.Serializer::class)
data class ShardData(val index: Int, val count: Int) {

    internal object Serializer : KSerializer<ShardData> {

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor
            get() = listSerialDescriptor(PrimitiveSerialDescriptor("dev.kord.discord.objects.ShardData", PrimitiveKind.INT))

        override fun serialize(encoder: Encoder, value: ShardData) {
            encoder.encodeSerializableValue(ListSerializer(Int.serializer()), listOf(value.index, value.count))
        }

        override fun deserialize(decoder: Decoder): ShardData {
            val (index, count) = decoder.decodeSerializableValue(ListSerializer(Int.serializer()))
            return ShardData(index, count)
        }

    }

}
