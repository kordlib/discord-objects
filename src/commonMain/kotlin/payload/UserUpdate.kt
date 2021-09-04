package dev.kord.discord.objects.payload

import dev.kord.discord.objects.DiscordUser

class UserUpdate(
    override val data: DiscordUser,
    override val sequence: Int
) : DispatchEvent<DiscordUser>() {
    override val name: EventName get() = EventName.UserUpdate
}
