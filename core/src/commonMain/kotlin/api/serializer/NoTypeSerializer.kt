package dev.kord.discord.objects.api.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Serializer used to bypass kotlinx.serialization's generic type serializers if the generic type is not required
 * by the serializer object.
 */
@OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
internal object NoTypeSerializer : KSerializer<Any> {

    override val descriptor: SerialDescriptor = buildSerialDescriptor(
        "dev.kord.discord.objects.api.serializer.TokenSerializer",
        SerialKind.CONTEXTUAL
    )

    override fun serialize(encoder: Encoder, value: Any) {
        throw SerializationException("Cannot serialize")
    }

    override fun deserialize(decoder: Decoder): Any {
        throw SerializationException("Cannot deserialize")
    }

}
