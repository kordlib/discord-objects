package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordAddedGuildMember
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildMemberAdd.Serializer::class)
data class GuildMemberAdd(
    override val data: DiscordAddedGuildMember,
    override val sequence: Int
): Dispatch<DiscordAddedGuildMember>() {
    override val name: EventName get() = EventName.GuildMemberAdd

    internal object Serializer : KSerializer<GuildMemberAdd> by DispatchSerializer(::GuildMemberAdd)
}
