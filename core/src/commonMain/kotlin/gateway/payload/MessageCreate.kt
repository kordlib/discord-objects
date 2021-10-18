package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.MessageData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MessageCreate.Serializer::class)
data class MessageCreate(
    override val data: MessageData,
    override val sequence: Int
): Dispatch<MessageData>() {
    override val name: EventName get() = EventName.MessageCreate

    internal object Serializer : KSerializer<MessageCreate> by DispatchSerializer(::MessageCreate)
}
