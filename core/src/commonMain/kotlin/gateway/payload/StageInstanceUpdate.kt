package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordStageInstance
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StageInstanceUpdate.Serializer::class)
data class StageInstanceUpdate(
    override val data: DiscordStageInstance,
    override val sequence: Int
) : Dispatch<DiscordStageInstance>() {

    override val name: EventName
        get() = EventName.StageInstanceUpdate

    internal object Serializer : KSerializer<StageInstanceUpdate> by DispatchSerializer(::StageInstanceUpdate)

}
