package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.EmojiData
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = GuildEmojisUpdate.Serializer::class)
data class GuildEmojisUpdate(
    override val data: Data,
    override val sequence: Int
) : Dispatch<GuildEmojisUpdate.Data>() {

    override val name: EventName get() = EventName.GuildEmojisUpdate

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
        val emojis: List<EmojiData>,
    )

    internal object Serializer : KSerializer<GuildEmojisUpdate> by DispatchSerializer(::GuildEmojisUpdate)
}
