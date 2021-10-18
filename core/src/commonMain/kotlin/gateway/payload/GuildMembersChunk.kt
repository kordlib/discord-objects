package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.GuildMemberData
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = GuildMembersChunk.Serializer::class)
data class GuildMembersChunk(
    override val data: Data,
    override val sequence: Int
): Dispatch<GuildMembersChunk.Data>() {
    override val name: EventName get() = EventName.GuildMembersChunk

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
        val members: List<GuildMemberData>,
        @SerialName("chunk_index")
        val chunkIndex: Int,
        @SerialName("chunk_count")
        val chunkCount: Int,
        @SerialName("not_found")
        val notFound: Optional<Set<Snowflake>> = Optional.Missing(),
        val presences: Optional<List<PresenceUpdate.Data>> = Optional.Missing(),
        val nonce: Optional<String> = Optional.Missing()
    )

    internal object Serializer : KSerializer<GuildMembersChunk> by DispatchSerializer(::GuildMembersChunk)
}
