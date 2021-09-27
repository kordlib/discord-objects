package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordInteraction
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = InteractionCreate.Serializer::class)
data class InteractionCreate(
    override val data: DiscordInteraction,
    override val sequence: Int
) : Dispatch<DiscordInteraction>() {
    override val name: EventName get() = EventName.InteractionCreate

    internal object Serializer : KSerializer<InteractionCreate> by DispatchSerializer(::InteractionCreate)
}
