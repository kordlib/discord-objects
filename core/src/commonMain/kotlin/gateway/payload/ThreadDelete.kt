package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordChannel
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ThreadDelete.Serializer::class)
data class ThreadDelete(
    override val data: DiscordChannel,
    override val sequence: Int
) : Dispatch<DiscordChannel>() {
    override val name: EventName get() = EventName.ThreadDelete

    internal object Serializer : KSerializer<ThreadDelete> by DispatchSerializer(::ThreadDelete)
}
