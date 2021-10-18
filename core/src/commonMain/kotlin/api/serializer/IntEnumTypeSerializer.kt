package dev.kord.discord.objects.api.serializer

import dev.kord.discord.objects.api.EnumType
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor


abstract class IntEnumTypeSerializer<T : EnumType<Int>>(
    className: String,
    values: Set<T>,
    unknown: (Int) -> T
) : EnumTypeSerializer<T, Int>(
    values,
    unknown,
    Int.serializer()
) {

    final override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        "dev.kord.discord.objects.$className", PrimitiveKind.INT
    )

}
