package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordChannel
import dev.kord.discord.objects.DiscordShard
import dev.kord.discord.objects.DiscordUnavailableGuild
import dev.kord.discord.objects.DiscordUser
import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

class Ready(
    override val data: Data,
    override val sequence: Int
) : DispatchEvent<Ready.Data>() {
    override val name: EventName get() = EventName.Ready

    @Serializable
    data class Data(
        @SerialName("v")
        val version: Int,
        val user: DiscordUser,
        @SerialName("private_channels")
        val privateChannels: List<DiscordChannel>,
        val guilds: List<DiscordUnavailableGuild>,
        @SerialName("session_id")
        val sessionId: String,
        @SerialName("geo_ordered_rtc_regions")
        val geoOrderedRtcRegions: Optional<JsonElement?> = Optional.Missing(),
        @SerialName("guild_hashes")
        val guildHashes: Optional<JsonElement?> = Optional.Missing(),
        val application: Optional<JsonElement?> = Optional.Missing(),
        @SerialName("_trace")
        val traces: List<String>,
        val shard: Optional<DiscordShard> = Optional.Missing(),
    )

}
