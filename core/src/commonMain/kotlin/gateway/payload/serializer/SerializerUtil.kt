package dev.kord.discord.objects.gateway.payload.serializer

import dev.kord.discord.objects.Snowflake
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeEncoder

internal fun CompositeEncoder.encodeSnowflake(descriptor: SerialDescriptor, index: Int, value: Snowflake) {
    encodeSerializableElement(descriptor, index, Snowflake.serializer(), value)
}

internal fun CompositeDecoder.decodeSnowflake(descriptor: SerialDescriptor, index: Int): Snowflake {
    return decodeSerializableElement(descriptor, index, Snowflake.serializer())
}
