package dev.kord.discord.objects

import dev.kord.discord.objects.api.RawData
import kotlinx.serialization.ContextualSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@OptIn(ExperimentalSerializationApi::class)
internal object DiscordPresenceUserSerializer : KSerializer<DiscordPresenceUser> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
        "dev.kord.discord.objects.DiscordPresenceUser"
    ) {
        element<Snowflake>("id")
    }

    override fun deserialize(decoder: Decoder): DiscordPresenceUser {
        val obj = decoder.decodeSerializableValue(JsonObject.serializer()).toMutableMap()
        val id = obj.remove("id")!!
        decoder as JsonDecoder
        val idSnowflake = decoder.json.decodeFromJsonElement(Snowflake.serializer(), id)
        val rawData = decoder.json.decodeFromJsonElement(ContextualSerializer(RawData::class), JsonObject(obj))
        return DiscordPresenceUser(idSnowflake, rawData)
    }

    override fun serialize(encoder: Encoder, value: DiscordPresenceUser) {
        encoder as JsonEncoder
        val json = JsonObject(
            mapOf(
                "id" to encoder.json.encodeToJsonElement(Snowflake.serializer(), value.id)
            ) + encoder.json.encodeToJsonElement(ContextualSerializer(RawData::class), value.details).jsonObject
        )

        encoder.encodeJsonElement(json)
    }

}
