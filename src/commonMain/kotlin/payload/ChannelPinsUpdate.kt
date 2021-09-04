package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordPinsUpdateData

class ChannelPinsUpdate(
    override val data: DiscordPinsUpdateData,
    override val sequence: Int
) : DispatchEvent<DiscordPinsUpdateData>() {
    override val name: EventName get() = EventName.ChannelPinsUpdate

}
