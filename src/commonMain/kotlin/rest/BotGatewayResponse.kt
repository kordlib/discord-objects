package dev.kord.discord.objects.rest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BotGatewayResponse(
    val url: String,
    val shards: Int,
    @SerialName("session_start_limit")
    val sessionStartLimit: SessionStartLimitResponse
)
