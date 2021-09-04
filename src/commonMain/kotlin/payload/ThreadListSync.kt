package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordChannel
import dev.kord.discord.objects.DiscordThreadMember
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class ThreadListSync(
    override val data: Data,
    override val sequence: Int
) : DispatchEvent<ThreadListSync.Data>() {
    override val name: EventName get() = EventName.ThreadListSync

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
        @SerialName("channel_ids")
        val channelIds: Optional<List<Snowflake>> = Optional.Missing(),
        val threads: List<DiscordChannel>,
        val members: List<DiscordThreadMember>
    )

}
