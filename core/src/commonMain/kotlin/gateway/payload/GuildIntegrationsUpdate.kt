package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = GuildIntegrationsUpdate.Serializer::class)
data class GuildIntegrationsUpdate(
    override val data: Data,
    override val sequence: Int
) : Dispatch<GuildIntegrationsUpdate.Data>() {
    override val name: EventName get() = EventName.GuildIntegrationsUpdate

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
    )

    internal object Serializer : KSerializer<GuildIntegrationsUpdate> by DispatchSerializer(::GuildIntegrationsUpdate)
}
