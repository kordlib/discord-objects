package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.ChannelData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ThreadCreate.Serializer::class)
data class ThreadCreate(
    override val data: ChannelData,
    override val sequence: Int
) : Dispatch<ChannelData>() {
    override val name: EventName get() = EventName.ThreadCreate

    internal object Serializer : KSerializer<ThreadCreate> by DispatchSerializer(::ThreadCreate)
}
