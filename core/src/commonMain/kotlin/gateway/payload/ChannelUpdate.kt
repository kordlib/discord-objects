package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordChannel
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ChannelUpdate.Serializer::class)
data class ChannelUpdate(
    override val data: DiscordChannel,
    override val sequence: Int
) : Dispatch<DiscordChannel>() {
    override val name: EventName get() = EventName.ChannelUpdate

    internal object Serializer : KSerializer<ChannelUpdate> by DispatchSerializer(::ChannelUpdate)
}
