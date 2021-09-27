package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordGuildBan
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildBanAdd.Serializer::class)
data class GuildBanAdd(
    override val data: DiscordGuildBan,
    override val sequence: Int
) : Dispatch<DiscordGuildBan>() {
    override val name: EventName get() = EventName.GuildBanAdd

    internal object Serializer : KSerializer<GuildBanAdd> by DispatchSerializer(::GuildBanAdd)
}
