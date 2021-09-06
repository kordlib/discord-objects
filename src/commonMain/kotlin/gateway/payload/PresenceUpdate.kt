package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordPresenceUpdate

class PresenceUpdate(
    override val data: DiscordPresenceUpdate,
    override val sequence: Int
) : DispatchEvent<DiscordPresenceUpdate>() {
    override val name: EventName get() = EventName.PresenceUpdate

}
