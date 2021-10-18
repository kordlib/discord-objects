package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.IntegrationData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = IntegrationCreate.Serializer::class)
data class IntegrationCreate(
    override val data: IntegrationData,
    override val sequence: Int
) : Dispatch<IntegrationData>() {
    override val name: EventName get() = EventName.IntegrationCreate

    internal object Serializer : KSerializer<IntegrationCreate> by DispatchSerializer(::IntegrationCreate)

}
