package dev.kord.discord.objects.api.serializer

import dev.kord.discord.objects.api.EnumType
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor

abstract class StringEnumTypeSerializer<T : EnumType<String>>(
    className: String,
    values: Set<T>,
    unknown: (String) -> T
) : EnumTypeSerializer<T, String>(
    values,
    unknown,
    String.serializer()
) {

    final override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        "dev.kord.discord.objects.$className", PrimitiveKind.INT
    )

}
