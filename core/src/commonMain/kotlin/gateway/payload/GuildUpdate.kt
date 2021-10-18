package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.GuildData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildUpdate.Serializer::class)
data class GuildUpdate(
    override val data: GuildData,
    override val sequence: Int
) : Dispatch<GuildData>() {
    override val name: EventName get() = EventName.GuildUpdate

    internal object Serializer : KSerializer<GuildUpdate> by DispatchSerializer(::GuildUpdate)
}
