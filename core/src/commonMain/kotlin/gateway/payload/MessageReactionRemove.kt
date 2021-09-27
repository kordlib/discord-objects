package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.MessageReactionRemoveData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MessageReactionRemove.Serializer::class)
data class MessageReactionRemove(
    override val data: MessageReactionRemoveData,
    override val sequence: Int
) : Dispatch<MessageReactionRemoveData>() {
    override val name: EventName get() = EventName.MessageReactionRemove

    internal object Serializer : KSerializer<MessageReactionRemove> by DispatchSerializer(::MessageReactionRemove)
}
