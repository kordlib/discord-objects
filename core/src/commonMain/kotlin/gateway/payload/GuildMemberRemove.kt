package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordRemovedGuildMember
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildMemberRemove.Serializer::class)
data class GuildMemberRemove(
    override val data: DiscordRemovedGuildMember,
    override val sequence: Int
): Dispatch<DiscordRemovedGuildMember>() {
    override val name: EventName get() = EventName.GuildMemberRemove

    internal object Serializer : KSerializer<GuildMemberRemove> by DispatchSerializer(::GuildMemberRemove)
}
