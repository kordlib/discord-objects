package dev.kord.discord.objects.payload

import dev.kord.discord.objects.MessageReactionRemoveData

class MessageReactionRemove(
    override val data: MessageReactionRemoveData,
    override val sequence: Int
) : DispatchEvent<MessageReactionRemoveData>() {
    override val name: EventName get() = EventName.MessageReactionRemove

}
