package dev.kord.discord.objects

import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.Serializable

@Serializable
data class ActivityEmojiData(
    val name: String,
    val id: OptionalSnowflake = OptionalSnowflake.Missing,
    val animated: OptionalBoolean = OptionalBoolean.Missing
)
