package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordGuildMember
import dev.kord.discord.objects.DiscordPresenceUpdate
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class GuildMembersChunk(
    override val data: Data,
    override val sequence: Int
): DispatchEvent<GuildMembersChunk.Data>() {
    override val name: EventName get() = EventName.GuildMembersChunk

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
        val members: List<DiscordGuildMember>,
        @SerialName("chunk_index")
        val chunkIndex: Int,
        @SerialName("chunk_count")
        val chunkCount: Int,
        @SerialName("not_found")
        val notFound: Optional<Set<Snowflake>> = Optional.Missing(),
        val presences: Optional<List<DiscordPresenceUpdate>> = Optional.Missing(),
        val nonce: Optional<String> = Optional.Missing()
    )

}
