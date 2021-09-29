package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = IntegrationUpdate.Serializer::class)
data class IntegrationUpdate(
    override val data: Data,
    override val sequence: Int
) : Dispatch<IntegrationUpdate.Data>() {

    override val name: EventName
        get() = EventName.IntegrationUpdate

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake
    )

    internal object Serializer : KSerializer<IntegrationUpdate> by DispatchSerializer(::IntegrationUpdate)
}
