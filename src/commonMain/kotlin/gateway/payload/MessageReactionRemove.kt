package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.MessageReactionRemoveData

data class MessageReactionRemove(
    override val data: MessageReactionRemoveData,
    override val sequence: Int
) : DispatchEvent<MessageReactionRemoveData>() {
    override val name: EventName get() = EventName.MessageReactionRemove

}
