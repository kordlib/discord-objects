package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordChannel
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ThreadUpdate.Serializer::class)
data class ThreadUpdate(
    override val data: DiscordChannel,
    override val sequence: Int
) : Dispatch<DiscordChannel>() {
    override val name: EventName get() = EventName.ThreadUpdate

    internal object Serializer : KSerializer<ThreadUpdate> by DispatchSerializer(::ThreadUpdate)
}
