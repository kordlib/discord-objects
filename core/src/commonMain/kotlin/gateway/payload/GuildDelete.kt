package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordUnavailableGuild
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildDelete.Serializer::class)
data class GuildDelete(
    override val data: DiscordUnavailableGuild,
    override val sequence: Int
) : Dispatch<DiscordUnavailableGuild>() {
    override val name: EventName get() = EventName.GuildDelete

    internal object Serializer : KSerializer<GuildDelete> by DispatchSerializer(::GuildDelete)
}
