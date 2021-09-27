package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonEncoder

internal object AuditLogChangeSerializer : KSerializer<AuditLogChange<*>> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
        "dev.kord.discord.objects.AuditLogChange"
    ) {
        element<JsonElement>("new_value")
        element<JsonElement>("old_value")
        element<String>("key")
    }

    @Serializable
    private data class Model(
        @SerialName("new_value")
        val new: JsonElement? = null,
        @SerialName("old_value")
        val old: JsonElement? = null,
        val key: AuditLogChangeKey<Unit>,
    )

    override fun deserialize(decoder: Decoder): AuditLogChange<*> {
        val delegate = decoder.decodeSerializableValue(Model.serializer())
        decoder as JsonDecoder

        val valueSerializer = delegate.key.serializer.nullable
        val new = run {
            decoder.json.decodeFromJsonElement(valueSerializer, delegate.new ?: return@run null)
        }
        val old = run {
            decoder.json.decodeFromJsonElement(valueSerializer, delegate.old ?: return@run null)
        }

        return AuditLogChange(new, old, delegate.key)
    }

    override fun serialize(encoder: Encoder, value: AuditLogChange<*>) {
        encoder as JsonEncoder
        value as AuditLogChange<Unit>
        val new = run {
            encoder.json.encodeToJsonElement(value.key.serializer, value.new ?: return@run null)
        }

        val old = run {
            encoder.json.encodeToJsonElement(value.key.serializer, value.old ?: return@run null)
        }

        encoder.encodeSerializableValue(Model.serializer(), Model(new = new, old = old, key = value.key))
    }

}
