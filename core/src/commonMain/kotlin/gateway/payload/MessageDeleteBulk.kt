package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.BulkDeleteData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MessageDeleteBulk.Serializer::class)
data class MessageDeleteBulk(
    override val data: BulkDeleteData,
    override val sequence: Int
) : Dispatch<BulkDeleteData>() {
    override val name: EventName get() = EventName.MessageDeleteBulk

    internal object Serializer : KSerializer<MessageDeleteBulk> by DispatchSerializer(::MessageDeleteBulk)
}
