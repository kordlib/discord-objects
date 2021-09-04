package dev.kord.discord.objects.optional

import dev.kord.discord.objects.optional.Optional.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal class OptionalSerializer<T>(
    private val contentSerializer: KSerializer<T>
) : KSerializer<Optional<T>> {
    override val descriptor: SerialDescriptor = contentSerializer.descriptor

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): Optional<T> {
        /**
         * let's clear up any inconsistencies, an Optional cannot be <T: Any> and be represented as nullable.
         */
        if (!descriptor.isNullable && !decoder.decodeNotNullMark()) {
            throw SerializationException(
                "descriptor for ${descriptor.serialName} was not nullable but null mark was encountered"
            )
        }

        /**
         * This is rather ugly; I can't figure out a way to convince the compiler that <T> isn't nullable,
         * we have personally proven above that the serializer cannot return null so we'll just act as if we
         * know what we're doing.
         */
        val optional: Optional<T?> = when {
            !decoder.decodeNotNullMark() -> {
                decoder.decodeNull()
                Optional.Null<Nothing>()
            }
            else -> Optional(decoder.decodeSerializableValue(contentSerializer))
        }

        @Suppress("UNCHECKED_CAST")
        return optional as Optional<T>
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: Optional<T>) = when (value) {
        is Missing<*> -> throw SerializationException("missing values cannot be serialized")
        is Null<*> -> encoder.encodeNull()
        is Value -> encoder.encodeSerializableValue(contentSerializer, value.value)
    }
}
