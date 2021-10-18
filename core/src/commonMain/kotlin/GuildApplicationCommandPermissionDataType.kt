package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = GuildApplicationCommandPermissionDataType.Serializer::class)
sealed class GuildApplicationCommandPermissionDataType(val value: Int) {
    object Role : GuildApplicationCommandPermissionDataType(1)
    object User : GuildApplicationCommandPermissionDataType(2)
    class Unknown(value: Int) : GuildApplicationCommandPermissionDataType(value)

    object Serializer : KSerializer<GuildApplicationCommandPermissionDataType> {
        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("type", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): GuildApplicationCommandPermissionDataType =
            when (val value = decoder.decodeInt()) {
                1 -> Role
                2 -> User
                else -> Unknown(value)
            }

        override fun serialize(encoder: Encoder, value: GuildApplicationCommandPermissionDataType) = encoder.encodeInt(value.value)
    }
}
