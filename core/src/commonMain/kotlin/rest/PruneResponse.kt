package dev.kord.discord.objects.rest

import kotlinx.serialization.Serializable

@Serializable
data class PruneResponse(val pruned: Int?)
