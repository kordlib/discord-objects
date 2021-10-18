package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.ChannelData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ChannelDelete.Serializer::class)
data class ChannelDelete(
    override val data: ChannelData,
    override val sequence: Int
) : Dispatch<ChannelData>() {
    override val name: EventName get() = EventName.ChannelDelete

    internal object Serializer : KSerializer<ChannelDelete> by DispatchSerializer(::ChannelDelete)

}
