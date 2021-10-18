package dev.kord.discord.objects

import dev.kord.discord.objects.optional.OptionalBoolean
import kotlinx.serialization.Serializable

@Serializable
data class ComponentEmojiData(
    val name: String,
    val id: Snowflake?,
    val animated: OptionalBoolean = OptionalBoolean.Missing()
)
