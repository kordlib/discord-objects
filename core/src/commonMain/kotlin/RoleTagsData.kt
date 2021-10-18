package dev.kord.discord.objects

import dev.kord.discord.objects.api.DiscordNull
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoleTagsData(
    val botId: OptionalSnowflake = OptionalSnowflake.Missing,
    @SerialName("integration_id")
    val integrationId: OptionalSnowflake = OptionalSnowflake.Missing,
    @SerialName("premium_subscriber")
    val premiumSubscriber: Optional<DiscordNull?> = Optional.Missing(),
)
