package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.UnavailableGuildData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildDelete.Serializer::class)
data class GuildDelete(
    override val data: UnavailableGuildData,
    override val sequence: Int
) : Dispatch<UnavailableGuildData>() {
    override val name: EventName get() = EventName.GuildDelete

    internal object Serializer : KSerializer<GuildDelete> by DispatchSerializer(::GuildDelete)
}
