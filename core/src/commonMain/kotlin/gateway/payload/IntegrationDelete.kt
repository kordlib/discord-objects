package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = IntegrationDelete.Serializer::class)
data class IntegrationDelete(
    override val data: Data,
    override val sequence: Int
) : Dispatch<IntegrationDelete.Data>() {

    override val name: EventName
        get() = EventName.IntegrationDelete
    
    @Serializable
    data class Data(
        val id: Snowflake,
        @SerialName("guild_id")
        val guildId: Snowflake,
        @SerialName("application_id")
        val applicationId: OptionalSnowflake = OptionalSnowflake.Missing
    )
    
    internal object Serializer : KSerializer<IntegrationDelete> by DispatchSerializer(::IntegrationDelete)

}
