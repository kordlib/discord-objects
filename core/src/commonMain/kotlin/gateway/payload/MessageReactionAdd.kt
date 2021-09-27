package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.MessageReactionAddData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MessageReactionAdd.Serializer::class)
data class MessageReactionAdd(
    override val data: MessageReactionAddData,
    override val sequence: Int
) : Dispatch<MessageReactionAddData>() {
    override val name: EventName get() = EventName.MessageReactionAdd

    internal object Serializer : KSerializer<MessageReactionAdd> by DispatchSerializer(::MessageReactionAdd)
}
