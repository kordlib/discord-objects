package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.UserData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = GuildBanAdd.Serializer::class)
data class GuildBanAdd(
    override val data: Data,
    override val sequence: Int
) : Dispatch<GuildBanAdd.Data>() {
    override val name: EventName get() = EventName.GuildBanAdd

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: String,
        val user: UserData,
    )

    internal object Serializer : KSerializer<GuildBanAdd> by DispatchSerializer(::GuildBanAdd)
}
