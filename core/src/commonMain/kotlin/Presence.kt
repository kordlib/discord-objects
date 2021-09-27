package dev.kord.discord.objects

import dev.kord.discord.objects.api.RawData
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class DiscordPresenceUpdate(
    val user: @Contextual DiscordPresenceUser,
    /*
    Don't trust the docs:
    2020-11-05: Discord documentation incorrectly claims this field is non-optional,
    yet it is not present during the READY event.
    */
    @SerialName("guild_id")
    val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
    val status: PresenceStatus,
    val activities: List<DiscordActivity>,
    @SerialName("client_status")
    val clientStatus: DiscordClientStatus,
)

data class DiscordPresenceUser(
    val id: Snowflake,
    val details: RawData,
)

@Serializable
data class DiscordClientStatus(
    val desktop: Optional<PresenceStatus> = Optional.Missing(),
    val mobile: Optional<PresenceStatus> = Optional.Missing(),
    val web: Optional<PresenceStatus> = Optional.Missing(),
)

@Serializable(with = PresenceStatus.StatusSerializer::class)
sealed class PresenceStatus(val value: String) {

    class Unknown(value: String) : PresenceStatus(value)
    object Online : PresenceStatus("online")
    object Idle : PresenceStatus("idle")
    object DoNotDisturb : PresenceStatus("dnd")
    object Offline : PresenceStatus("offline")
    object Invisible : PresenceStatus("invisible")

    companion object StatusSerializer : KSerializer<PresenceStatus> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Kord.ClientStatus", PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): PresenceStatus = when (val value = decoder.decodeString()) {
            "online" -> Online
            "idle" -> Idle
            "dnd" -> DoNotDisturb
            "offline" -> Offline
            "invisible" -> Invisible
            else -> Unknown(value)
        }

        override fun serialize(encoder: Encoder, value: PresenceStatus) {
            encoder.encodeString(value.value)
        }
    }
}
