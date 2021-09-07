package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.BulkDeleteData

data class MessageBulkDelete(
    override val data: BulkDeleteData,
    override val sequence: Int
) : DispatchEvent<BulkDeleteData>() {
    override val name: EventName get() = EventName.MessageBulkDelete

}
