package dev.kord.discord.objects

import kotlinx.serialization.Serializable

/**
 * A representation of the [Discord Voice Region structure](https://discord.com/developers/docs/resources/voice#voice-region-object).
 *
 * @param id Unique id for the region.
 * @param name Name of the region.
 * @param vip True if this is a vip-only server.
 * @param optimal True for a single server that is closest to the current user's client.
 * @param deprecated Whether this is a deprecated voice server (avoid switching to these).
 * @param custom Whether this is a custom voice region (used for events/etc).
 */
@Serializable
data class VoiceRegionData(
    val id: String,
    val name: String,
    val optimal: Boolean,
    val deprecated: Boolean,
    val custom: Boolean,
)
