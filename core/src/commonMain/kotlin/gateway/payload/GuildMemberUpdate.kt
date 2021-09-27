package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordUpdatedGuildMember
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildMemberUpdate.Serializer::class)
data class GuildMemberUpdate(
    override val data: DiscordUpdatedGuildMember,
    override val sequence: Int
) : Dispatch<DiscordUpdatedGuildMember>() {
    override val name: EventName get() = EventName.GuildMemberUpdate

    internal object Serializer : KSerializer<GuildMemberUpdate> by DispatchSerializer(::GuildMemberUpdate)
}
