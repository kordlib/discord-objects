package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.UserData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = GuildBanRemove.Serializer::class)
data class GuildBanRemove(
    override val data: Data,
    override val sequence: Int
) : Dispatch<GuildBanRemove.Data>() {
    override val name: EventName get() = EventName.GuildBanRemove

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: String,
        val user: UserData,
    )

    internal object Serializer : KSerializer<GuildBanRemove> by DispatchSerializer(::GuildBanRemove)
}
