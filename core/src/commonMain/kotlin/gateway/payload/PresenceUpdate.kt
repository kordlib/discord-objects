package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordPresenceUpdate
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = PresenceUpdate.Serializer::class)
data class PresenceUpdate(
    override val data: DiscordPresenceUpdate,
    override val sequence: Int
) : Dispatch<DiscordPresenceUpdate>() {
    override val name: EventName get() = EventName.PresenceUpdate

    internal object Serializer : KSerializer<PresenceUpdate> by DispatchSerializer(::PresenceUpdate)
}
