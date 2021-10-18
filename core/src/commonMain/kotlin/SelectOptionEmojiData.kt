package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.Serializable

@Serializable
data class SelectOptionEmojiData(
    val id: OptionalSnowflake = OptionalSnowflake.Missing,
    val name: Optional<String> = Optional.Missing(),
    val animated: OptionalBoolean = OptionalBoolean.Missing()
)
