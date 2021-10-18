package dev.kord.discord.objects.api.serializer

import dev.kord.discord.objects.api.EnumType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

abstract class EnumTypeSerializer<T : EnumType<R>, R>(
    private val values: Set<T>,
    private val unknown: (R) -> T,
    private val valueSerializer: KSerializer<R>
) : KSerializer<T> {

    final override fun serialize(encoder: Encoder, value: T) {
        encoder.encodeSerializableValue(valueSerializer, value.value)
    }

    final override fun deserialize(decoder: Decoder): T {
        val value = decoder.decodeSerializableValue(valueSerializer)
        return values.firstOrNull { it.value == value } ?: unknown(value)
    }

}
