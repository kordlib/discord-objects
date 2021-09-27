package dev.kord.discord.objects.api

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement

@Serializable(with = JsonRawData.Serializer::class)
data class JsonRawData(val json: JsonElement) : RawData {

    internal object Serializer : KSerializer<JsonRawData> {

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
            "dev.kord.discord.objects.api.JsonRawData"
        )

        override fun deserialize(decoder: Decoder): JsonRawData {
            return JsonRawData(decoder.decodeSerializableValue(JsonElement.serializer()))
        }

        override fun serialize(encoder: Encoder, value: JsonRawData) {
            encoder.encodeSerializableValue(JsonElement.serializer(), value.json)
        }

    }

}
