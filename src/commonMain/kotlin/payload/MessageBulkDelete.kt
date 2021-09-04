package dev.kord.discord.objects.payload

import dev.kord.discord.objects.BulkDeleteData

class MessageBulkDelete(
    override val data: BulkDeleteData,
    override val sequence: Int
) : DispatchEvent<BulkDeleteData>() {
    override val name: EventName get() = EventName.MessageBulkDelete

}
