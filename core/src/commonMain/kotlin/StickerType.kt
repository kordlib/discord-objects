package dev.kord.discord.objects

import dev.kord.discord.objects.api.EnumType
import dev.kord.discord.objects.api.EnumTypeCompanion
import dev.kord.discord.objects.api.serializer.IntEnumTypeSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StickerType.Serializer::class)
sealed class StickerType(override val value: Int) : EnumType<Int> {
    class Unknown(value: Int) : StickerType(value)
    object Standard : StickerType(1)
    object Guild : StickerType(2)

    companion object : EnumTypeCompanion<StickerType> {
        override val values: Set<StickerType>
            get() = setOf(Standard, Guild)
    }

    internal object Serializer : IntEnumTypeSerializer<StickerType>(
        "StickerType", values, ::Unknown
    )

}
