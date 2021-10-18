package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.ChannelData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ChannelUpdate.Serializer::class)
data class ChannelUpdate(
    override val data: ChannelData,
    override val sequence: Int
) : Dispatch<ChannelData>() {
    override val name: EventName get() = EventName.ChannelUpdate

    internal object Serializer : KSerializer<ChannelUpdate> by DispatchSerializer(::ChannelUpdate)
}
