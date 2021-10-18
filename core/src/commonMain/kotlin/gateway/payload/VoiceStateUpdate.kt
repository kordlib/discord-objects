package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.VoiceStateData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = VoiceStateUpdate.Serializer::class)
data class VoiceStateUpdate(
    override val data: VoiceStateData,
    override val sequence: Int
) : Dispatch<VoiceStateData>() {
    override val name: EventName get() = EventName.VoiceStateUpdate

    internal object Serializer : KSerializer<VoiceStateUpdate> by DispatchSerializer(::VoiceStateUpdate)
}
