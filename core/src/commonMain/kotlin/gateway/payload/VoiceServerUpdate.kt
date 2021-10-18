package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = VoiceServerUpdate.Serializer::class)
data class VoiceServerUpdate(
    override val data: Data,
    override val sequence: Int
) : Dispatch<VoiceServerUpdate.Data>() {
    override val name: EventName get() = EventName.VoiceServerUpdate

    /**
     * @param token The voice connection token.
     * @param guildId The guild id this server update is for.
     * @param endpoint The voice server host.
     * A null endpoint means that the voice server allocated has gone away and is trying to be reallocated.
     * You should attempt to disconnect from the currently connected voice server,
     * and not attempt to reconnect until a new voice server is allocated.
     */
    @Serializable
    data class Data(
        val token: String,
        @SerialName("guild_id")
        val guildId: Snowflake,
        val endpoint: String?,
    )

    internal object Serializer : KSerializer<VoiceServerUpdate> by DispatchSerializer(::VoiceServerUpdate)
}
