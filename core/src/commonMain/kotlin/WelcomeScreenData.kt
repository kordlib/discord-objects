package dev.kord.discord.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WelcomeScreenData(
    val description: String?,
    @SerialName("welcome_channels")
    val welcomeChannels: List<WelcomeScreenChannelData>
)
