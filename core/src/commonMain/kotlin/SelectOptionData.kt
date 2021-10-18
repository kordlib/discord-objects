package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import kotlinx.serialization.Serializable

/**
 * Represent a [select option structure](https://discord.com/developers/docs/interactions/message-components#select-menu-object-select-option-structure).
 *
 * @param label the user-facing name of the option, max 25 characters
 * @param value the dev-define value of the option, max 100 characters
 * @param description an additional description of the option, max 50 characters
 * @param emoji the emoji to show in the option
 * @param default whether to render this option as selected by default
 */
@Serializable
data class SelectOptionData(
    val label: String,
    val value: String,
    val description: Optional<String> = Optional.Missing(),
    val emoji: Optional<SelectOptionEmojiData> = Optional.Missing(),
    val default: OptionalBoolean = OptionalBoolean.Missing,
)
