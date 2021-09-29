package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordSticker
import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable(with = GuildStickersUpdate.Serializer::class)
data class GuildStickersUpdate(
    override val data: Data,
    override val sequence: Int
) : Dispatch<GuildStickersUpdate.Data>() {

    override val name: EventName
        get() = EventName.GuildStickersUpdate

    @Serializable
    data class Data(
        @SerialName("guild_info")
        val guildId: Snowflake,
        val stickers: List<DiscordSticker>
    )

    internal object Serializer : KSerializer<GuildStickersUpdate> by DispatchSerializer(::GuildStickersUpdate)

}
