package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActivityData(
    val name: String,
    val type: ActivityType,
    val url: Optional<String?> = Optional.Missing(),
    @SerialName("created_at")
    val createdAt: Long,
    val timestamps: Optional<ActivityTimeStampsData> = Optional.Missing(),
    @SerialName("application_id")
    val applicationId: OptionalSnowflake = OptionalSnowflake.Missing,
    val details: Optional<String?> = Optional.Missing(),
    val state: Optional<String?> = Optional.Missing(),
    val emoji: Optional<ActivityEmojiData?> = Optional.Missing(),
    val party: Optional<ActivityPartyData> = Optional.Missing(),
    val assets: Optional<ActivityAssetsData> = Optional.Missing(),
    val secrets: Optional<ActivitySecretsData> = Optional.Missing(),
    val instance: OptionalBoolean = OptionalBoolean.Missing,
    val flags: Optional<ActivityFlags> = Optional.Missing(),
    val buttons: Optional<List<String>> = Optional.Missing()
)
