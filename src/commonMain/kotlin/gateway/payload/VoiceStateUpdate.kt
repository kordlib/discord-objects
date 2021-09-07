package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordVoiceState

data class VoiceStateUpdate(
    override val data: DiscordVoiceState,
    override val sequence: Int
) : DispatchEvent<DiscordVoiceState>() {
    override val name: EventName get() = EventName.VoiceStateUpdate

}
