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
@Serializable(with = DiscordShard.Companion::class)
data class DiscordShard(val index: Int, val count: Int) {

    companion object : KSerializer<DiscordShard> {

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor
            get() = listSerialDescriptor(PrimitiveSerialDescriptor("DiscordShardElement", PrimitiveKind.INT))

        override fun serialize(encoder: Encoder, value: DiscordShard) {
            encoder.encodeSerializableValue(ListSerializer(Int.serializer()), listOf(value.index, value.count))
        }

        override fun deserialize(decoder: Decoder): DiscordShard {
            val (index, count) = decoder.decodeSerializableValue(ListSerializer(Int.serializer()))
            return DiscordShard(index, count)
        }

    }

}
