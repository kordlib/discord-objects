package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordUpdatedEmojis
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildEmojisUpdate.Serializer::class)
data class GuildEmojisUpdate(
    override val data: DiscordUpdatedEmojis,
    override val sequence: Int
) : Dispatch<DiscordUpdatedEmojis>() {

    override val name: EventName get() = EventName.GuildEmojisUpdate

    internal object Serializer : KSerializer<GuildEmojisUpdate> by DispatchSerializer(::GuildEmojisUpdate)
}
