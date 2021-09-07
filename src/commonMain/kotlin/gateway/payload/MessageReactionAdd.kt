package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.MessageReactionAddData

data class MessageReactionAdd(
    override val data: MessageReactionAddData,
    override val sequence: Int
) : DispatchEvent<MessageReactionAddData>() {
    override val name: EventName get() = EventName.MessageReactionAdd

}
