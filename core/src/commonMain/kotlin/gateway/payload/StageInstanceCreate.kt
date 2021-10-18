package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.StageInstanceData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StageInstanceCreate.Serializer::class)
data class StageInstanceCreate(
    override val data: StageInstanceData,
    override val sequence: Int
) : Dispatch<StageInstanceData>() {

    override val name: EventName
        get() = EventName.StageInstanceCreate

    internal object Serializer : KSerializer<StageInstanceCreate> by DispatchSerializer(::StageInstanceCreate)

}
