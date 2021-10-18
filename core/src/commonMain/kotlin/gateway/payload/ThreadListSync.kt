package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.ChannelData
import dev.kord.discord.objects.ThreadMemberData
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ThreadListSync.Serializer::class)
data class ThreadListSync(
    override val data: Data,
    override val sequence: Int
) : Dispatch<ThreadListSync.Data>() {
    override val name: EventName get() = EventName.ThreadListSync

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
        @SerialName("channel_ids")
        val channelIds: Optional<List<Snowflake>> = Optional.Missing(),
        val threads: List<ChannelData>,
        val members: List<ThreadMemberData>
    )

    internal object Serializer : KSerializer<ThreadListSync> by DispatchSerializer(::ThreadListSync)
}
