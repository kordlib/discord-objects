package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordGuildBan
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildBanRemove.Serializer::class)
data class GuildBanRemove(
    override val data: DiscordGuildBan,
    override val sequence: Int
) : Dispatch<DiscordGuildBan>() {
    override val name: EventName get() = EventName.GuildBanRemove

    internal object Serializer : KSerializer<GuildBanRemove> by DispatchSerializer(::GuildBanRemove)
}
