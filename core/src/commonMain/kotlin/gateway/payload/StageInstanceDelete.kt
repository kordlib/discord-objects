package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.StageInstanceData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StageInstanceDelete.Serializer::class)
data class StageInstanceDelete(
    override val data: StageInstanceData,
    override val sequence: Int
) : Dispatch<StageInstanceData>() {

    override val name: EventName
        get() = EventName.StageInstanceDelete

    internal object Serializer : KSerializer<StageInstanceDelete> by DispatchSerializer(::StageInstanceDelete)

}
