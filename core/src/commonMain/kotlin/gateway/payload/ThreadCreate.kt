package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordChannel
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ThreadCreate.Serializer::class)
data class ThreadCreate(
    override val data: DiscordChannel,
    override val sequence: Int
) : Dispatch<DiscordChannel>() {
    override val name: EventName get() = EventName.ThreadCreate

    internal object Serializer : KSerializer<ThreadCreate> by DispatchSerializer(::ThreadCreate)
}
