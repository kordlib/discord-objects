package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordChannel
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ChannelCreate.Serializer::class)
data class ChannelCreate(
    override val data: DiscordChannel,
    override val sequence: Int
) : Dispatch<DiscordChannel>() {
    override val name: EventName get() = EventName.ChannelCreate

    internal object Serializer : KSerializer<ChannelCreate> by DispatchSerializer(::ChannelCreate)
}
