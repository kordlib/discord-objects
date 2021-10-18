package dev.kord.discord.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SessionStartLimitData(
    val total: Int,
    val remaining: Int,
    @SerialName("reset_after")
    val resetAfter: Int,
    @SerialName("max_concurrency")
    val maxConcurrency: Int
)
