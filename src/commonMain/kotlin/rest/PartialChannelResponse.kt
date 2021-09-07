package dev.kord.discord.objects.rest

import dev.kord.discord.objects.ChannelType
import kotlinx.serialization.Serializable

@Serializable
data class PartialChannelResponse(val name: String, val type: ChannelType)
