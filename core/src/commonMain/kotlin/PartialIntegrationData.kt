package dev.kord.discord.objects

import kotlinx.serialization.Serializable

@Serializable
data class PartialIntegrationData(
    val id: Snowflake,
    val name: String,
    val type: String,
    val account: IntegrationsAccountData,
)
