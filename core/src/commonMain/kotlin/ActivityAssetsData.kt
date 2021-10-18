package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActivityAssetsData(
    @SerialName("large_image")
    val largeImage: Optional<String> = Optional.Missing(),
    @SerialName("large_text")
    val largeText: Optional<String> = Optional.Missing(),
    @SerialName("small_image")
    val smallImage: Optional<String> = Optional.Missing(),
    @SerialName("small_text")
    val smallText: Optional<String> = Optional.Missing()
)
