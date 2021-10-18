package dev.kord.discord.objects

import kotlinx.serialization.Serializable

@Serializable
data class PartialInviteData(
    val code: String?,
    val uses: Int
)
