package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.PartialMessageData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MessageUpdate.Serializer::class)
data class MessageUpdate(
    override val data: PartialMessageData,
    override val sequence: Int
) : Dispatch<PartialMessageData>() {
    override val name: EventName get() = EventName.MessageUpdate

    internal object Serializer : KSerializer<MessageUpdate> by DispatchSerializer(::MessageUpdate)
}
