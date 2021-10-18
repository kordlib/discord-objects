package dev.kord.discord.objects

import kotlinx.serialization.Serializable

@Serializable
data class MessageInteractionData(
    val id: Snowflake,
    val type: InteractionType,
    val name: String,
    val user: UserData
)
