package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(ApplicationCommandOptionType.Serializer::class)
sealed class ApplicationCommandOptionType(val type: Int) {

    object SubCommand : ApplicationCommandOptionType(1)
    object SubCommandGroup : ApplicationCommandOptionType(2)
    object String : ApplicationCommandOptionType(3)
    object Integer : ApplicationCommandOptionType(4)
    object Boolean : ApplicationCommandOptionType(5)
    object User : ApplicationCommandOptionType(6)
    object Channel : ApplicationCommandOptionType(7)
    object Role : ApplicationCommandOptionType(8)
    object Mentionable : ApplicationCommandOptionType(9)
    object Number : ApplicationCommandOptionType(10)
    class Unknown(type: Int) : ApplicationCommandOptionType(type)

    companion object {
        val values: Set<ApplicationCommandOptionType>
            get() = setOf(
                SubCommand,
                SubCommandGroup,
                String,
                Integer,
                Boolean,
                User,
                Channel,
                Role,
                Mentionable,
                Number
            )
    }

    internal object Serializer : KSerializer<ApplicationCommandOptionType> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("ApplicationCommandOptionType", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): ApplicationCommandOptionType {
            return when (val type = decoder.decodeInt()) {
                1 -> SubCommand
                2 -> SubCommandGroup
                3 -> String
                4 -> Integer
                5 -> Boolean
                6 -> User
                7 -> Channel
                8 -> Role
                9 -> Mentionable
                10 -> Number
                else -> Unknown(type)
            }
        }

        override fun serialize(encoder: Encoder, value: ApplicationCommandOptionType) {
            encoder.encodeInt(value.type)
        }
    }

}
