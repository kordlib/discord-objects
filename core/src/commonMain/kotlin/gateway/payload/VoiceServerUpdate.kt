package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordVoiceServerUpdateData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = VoiceServerUpdate.Serializer::class)
data class VoiceServerUpdate(
    override val data: DiscordVoiceServerUpdateData,
    override val sequence: Int
) : Dispatch<DiscordVoiceServerUpdateData>() {
    override val name: EventName get() = EventName.VoiceServerUpdate

    internal object Serializer : KSerializer<VoiceServerUpdate> by DispatchSerializer(::VoiceServerUpdate)
}
