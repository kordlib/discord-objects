package dev.kord.discord.objects

import dev.kord.discord.objects.UserData
import kotlinx.serialization.Serializable

@Serializable
data class BanData(
    val reason: String?,
    val user: UserData
)
