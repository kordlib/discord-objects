package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.AllRemovedMessageReactions

data class MessageReactionRemoveAll(
    override val data: AllRemovedMessageReactions,
    override val sequence: Int
) : DispatchEvent<AllRemovedMessageReactions>() {
    override val name: EventName get() = EventName.MessageReactionRemoveAll

}
