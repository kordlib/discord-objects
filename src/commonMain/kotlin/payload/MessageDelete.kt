package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DeletedMessage

class MessageDelete(
    override val data: DeletedMessage,
    override val sequence: Int
) : DispatchEvent<DeletedMessage>() {
    override val name: EventName get() = EventName.MessageDelete

}
