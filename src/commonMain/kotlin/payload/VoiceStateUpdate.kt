package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordVoiceState

class VoiceStateUpdate(
    override val data: DiscordVoiceState,
    override val sequence: Int
) : DispatchEvent<DiscordVoiceState>() {
    override val name: EventName get() = EventName.VoiceStateUpdate

}
