package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalInt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent a [intractable component within a message sent in Discord](https://discord.com/developers/docs/interactions/message-components#what-are-components).
 *
 * @property type the [ComponentType] of the component
 * @property style the [ButtonStyle] of the component (if it is a button)
 * @property style the text that appears on the button (if the component is a button)
 * @property emoji an [DiscordPartialEmoji] that appears on the button (if the component is a button)
 * @property customId a developer-defined identifier for the button, max 100 characters
 * @property url a url for link-style buttons
 * @property disabled whether the button is disabled, default `false`
 * @property components a list of child components (for action rows)
 * @property options the select menu options
 * @property placeholder the placeholder text for the select menu
 * @property minValues the minimum amount of [options] allowed
 * @property maxValues the maximum amount of [options] allowed
 */
@Serializable
data class ComponentData(
    val type: ComponentType,
    val style: Optional<ButtonStyle> = Optional.Missing(),
    val label: Optional<String> = Optional.Missing(),
    val emoji: Optional<ComponentEmojiData> = Optional.Missing(),
    @SerialName("custom_id")
    val customId: Optional<String> = Optional.Missing(),
    val url: Optional<String> = Optional.Missing(),
    val disabled: OptionalBoolean = OptionalBoolean.Missing,
    val components: Optional<List<ComponentData>> = Optional.Missing(),
    val options: Optional<List<SelectOptionData>> = Optional.Missing(),
    val placeholder: Optional<String> = Optional.Missing(),
    @SerialName("min_values")
    val minValues: OptionalInt = OptionalInt.Missing,
    @SerialName("max_values")
    val maxValues: OptionalInt = OptionalInt.Missing,
)
