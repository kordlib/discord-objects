package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.ChannelData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ChannelCreate.Serializer::class)
data class ChannelCreate(
    override val data: ChannelData,
    override val sequence: Int
) : Dispatch<ChannelData>() {
    override val name: EventName get() = EventName.ChannelCreate

    internal object Serializer : KSerializer<ChannelCreate> by DispatchSerializer(::ChannelCreate)
}
