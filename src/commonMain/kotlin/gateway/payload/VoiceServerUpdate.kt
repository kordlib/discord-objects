package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordVoiceServerUpdateData

class VoiceServerUpdate(
    override val data: DiscordVoiceServerUpdateData,
    override val sequence: Int
) : DispatchEvent<DiscordVoiceServerUpdateData>() {
    override val name: EventName get() = EventName.VoiceServerUpdate

}
