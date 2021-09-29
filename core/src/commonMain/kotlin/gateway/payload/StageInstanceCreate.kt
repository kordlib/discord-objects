package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordStageInstance
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StageInstanceCreate.Serializer::class)
data class StageInstanceCreate(
    override val data: DiscordStageInstance,
    override val sequence: Int
) : Dispatch<DiscordStageInstance>() {

    override val name: EventName
        get() = EventName.StageInstanceCreate

    internal object Serializer : KSerializer<StageInstanceCreate> by DispatchSerializer(::StageInstanceCreate)

}
