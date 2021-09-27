package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.AllRemovedMessageReactions
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MessageReactionRemoveAll.Serializer::class)
data class MessageReactionRemoveAll(
    override val data: AllRemovedMessageReactions,
    override val sequence: Int
) : Dispatch<AllRemovedMessageReactions>() {
    override val name: EventName get() = EventName.MessageReactionRemoveAll

    internal object Serializer : KSerializer<MessageReactionRemoveAll> by DispatchSerializer(::MessageReactionRemoveAll)
}
