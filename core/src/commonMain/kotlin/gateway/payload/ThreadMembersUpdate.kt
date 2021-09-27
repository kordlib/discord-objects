package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordThreadMember
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ThreadMembersUpdate.Serializer::class)
data class ThreadMembersUpdate(
    override val data: Data,
    override val sequence: Int
) : Dispatch<ThreadMembersUpdate.Data>() {
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

    internal object Serializer : KSerializer<ThreadMembersUpdate> by DispatchSerializer(::ThreadMembersUpdate)
}
