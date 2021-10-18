package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.InteractionData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = InteractionCreate.Serializer::class)
data class InteractionCreate(
    override val data: InteractionData,
    override val sequence: Int
) : Dispatch<InteractionData>() {
    override val name: EventName get() = EventName.InteractionCreate

    internal object Serializer : KSerializer<InteractionCreate> by DispatchSerializer(::InteractionCreate)
}
