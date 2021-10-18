package dev.kord.discord.objects

import dev.kord.discord.objects.api.serializer.NoTypeSerializer
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
class ApplicationCommandOptionData(
    val type: ApplicationCommandOptionType,
    val name: String,
    val description: String,
    val default: OptionalBoolean = OptionalBoolean.Missing,
    val required: OptionalBoolean = OptionalBoolean.Missing,
    val choices: Optional<List<@Contextual ApplicationCommandOptionChoiceData<@Serializable(with = NoTypeSerializer::class) Any>>> = Optional.Missing(),
    val options: Optional<List<ApplicationCommandOptionData>> = Optional.Missing(),
)
