package dev.kord.discord.objects.rest

import dev.kord.discord.objects.ChannelData
import dev.kord.discord.objects.ThreadMemberData
import kotlinx.serialization.Serializable

@Serializable
data class ListActiveThreadsData(
    val threads: List<ChannelData>,
    val members: List<ThreadMemberData>,
)
