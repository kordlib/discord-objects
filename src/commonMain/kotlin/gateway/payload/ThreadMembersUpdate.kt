package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordThreadMember
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


class ThreadMembersUpdate(
    override val data: Data,
    override val sequence: Int
) : DispatchEvent<ThreadMembersUpdate.Data>() {
    override val name: EventName get() = EventName.ThreadMemberUpdate

    @Serializable
    data class Data(
        val id: Snowflake,
        @SerialName("guild_id")
        val guildId: Snowflake,
        @SerialName("member_count")
        val memberCount: Int,
        @SerialName("added_members")
        val addedMembers: Optional<List<DiscordThreadMember>> = Optional.Missing(),
        @SerialName("removed_member_ids")
        val removedMemberIds: Optional<List<Snowflake>> = Optional.Missing()
    )

}
