package dev.kord.discord.objects

import kotlinx.serialization.Serializable

@Serializable
data class IntegrationAccountData(
    val id: String,
    val name: String,
)
