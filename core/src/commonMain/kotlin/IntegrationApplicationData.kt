package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.Serializable

@Serializable
data class IntegrationApplicationData(
    val id: Snowflake,
    val name: String,
    val icon: String?,
    val description: String,
    val summary: String,
    val bot: Optional<UserData>
)
