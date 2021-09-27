package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordPinsUpdateData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ChannelPinsUpdate.Serializer::class)
data class ChannelPinsUpdate(
    override val data: DiscordPinsUpdateData,
    override val sequence: Int
) : Dispatch<DiscordPinsUpdateData>() {
    override val name: EventName get() = EventName.ChannelPinsUpdate

    internal object Serializer : KSerializer<ChannelPinsUpdate> by DispatchSerializer(::ChannelPinsUpdate)
}
