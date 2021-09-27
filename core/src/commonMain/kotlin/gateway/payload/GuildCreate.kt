package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordGuild
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildCreate.Serializer::class)
data class GuildCreate(
    override val data: DiscordGuild,
    override val sequence: Int
) : Dispatch<DiscordGuild>() {
    override val name: EventName get() = EventName.GuildCreate

    internal object Serializer : KSerializer<GuildCreate> by DispatchSerializer(::GuildCreate)
}
