package dev.kord.discord.objects

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.*

internal object CommandArgumentSerializer : JsonContentPolymorphicSerializer<CommandArgument<*>>(
    CommandArgument::class
) {

    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out CommandArgument<*>> {
        val typeValue = element.jsonObject.getValue("type").jsonPrimitive.int
        val type = ApplicationCommandOptionType.values.first { it.type == typeValue }

        return when (type) {
            ApplicationCommandOptionType.Boolean -> CommandArgument.BooleanArgument.serializer()
            ApplicationCommandOptionType.Channel -> CommandArgument.ChannelArgument.serializer()
            ApplicationCommandOptionType.Integer -> CommandArgument.IntegerArgument.serializer()
            ApplicationCommandOptionType.Mentionable -> CommandArgument.MentionableArgument.serializer()
            ApplicationCommandOptionType.Number -> CommandArgument.NumberArgument.serializer()
            ApplicationCommandOptionType.Role -> CommandArgument.RoleArgument.serializer()
            ApplicationCommandOptionType.String -> CommandArgument.StringArgument.serializer()
            ApplicationCommandOptionType.User -> CommandArgument.UserArgument.serializer()
            ApplicationCommandOptionType.SubCommand,
            ApplicationCommandOptionType.SubCommandGroup,
            is ApplicationCommandOptionType.Unknown -> throw SerializationException(
                "Expected CommandArgument type but was $type"
            )
        }
    }

}
