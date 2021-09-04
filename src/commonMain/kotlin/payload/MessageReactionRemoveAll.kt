package dev.kord.discord.objects.payload

import dev.kord.discord.objects.AllRemovedMessageReactions

class MessageReactionRemoveAll(
    override val data: AllRemovedMessageReactions,
    override val sequence: Int
) : DispatchEvent<AllRemovedMessageReactions>() {
    override val name: EventName get() = EventName.MessageReactionRemoveAll

}
