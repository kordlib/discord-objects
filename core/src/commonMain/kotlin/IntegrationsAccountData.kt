package dev.kord.discord.objects

import kotlinx.serialization.Serializable

@Serializable
data class IntegrationsAccountData(
    val id: String,
    val name: String
)
