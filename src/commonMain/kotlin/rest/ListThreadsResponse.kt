package dev.kord.discord.objects.rest

import dev.kord.discord.objects.DiscordChannel
import dev.kord.discord.objects.DiscordThreadMember
import kotlinx.serialization.Serializable

@Serializable
data class ListThreadsResponse(
    val threads: List<DiscordChannel>,
    val members: List<DiscordThreadMember>,
)
