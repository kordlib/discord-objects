package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DeletedMessage
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MessageDelete.Serializer::class)
data class MessageDelete(
    override val data: DeletedMessage,
    override val sequence: Int
) : Dispatch<DeletedMessage>() {
    override val name: EventName get() = EventName.MessageDelete

    internal object Serializer : KSerializer<MessageDelete> by DispatchSerializer(::MessageDelete)
}
