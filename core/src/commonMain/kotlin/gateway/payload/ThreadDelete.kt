package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.ChannelData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ThreadDelete.Serializer::class)
data class ThreadDelete(
    override val data: ChannelData,
    override val sequence: Int
) : Dispatch<ChannelData>() {
    override val name: EventName get() = EventName.ThreadDelete

    internal object Serializer : KSerializer<ThreadDelete> by DispatchSerializer(::ThreadDelete)
}
