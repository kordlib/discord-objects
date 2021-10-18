package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.*
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = PresenceUpdate.Serializer::class)
data class PresenceUpdate(
    override val data: Data,
    override val sequence: Int
) : Dispatch<PresenceUpdate.Data>() {
    override val name: EventName get() = EventName.PresenceUpdate

    @Serializable
    data class Data(
        val user: UserData,
        /*
        Don't trust the docs:
        2020-11-05: Discord documentation incorrectly claims this field is non-optional,
        yet it is not present during the READY event.
        */
        @SerialName("guild_id")
        val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
        val status: PresenceStatus,
        val activities: List<ActivityData>,
        @SerialName("client_status")
        val clientStatus: ClientStatusData,
    )

    internal object Serializer : KSerializer<PresenceUpdate> by DispatchSerializer(::PresenceUpdate)
}
