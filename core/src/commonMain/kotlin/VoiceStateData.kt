package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A representation of the [Discord Voice State structure](https://discord.com/developers/docs/resources/voice#voice-state-object).
 * Used to represent a user's voice connection status.
 *
 * @param guildId the guild id this voice state is for.
 * @param channelId the channel id this user is connection to.
 * @param userId The user id this voice state is for.
 * @param member the guild member this voice state is for.
 * @param sessionId The session id for this voice state.
 * @param deaf Whether this user is deafened by the server.
 * @param mute Whether this user is muted by the server.
 * @param selfDeaf Whether this user is locally deafened.
 * @param selfMute Whether this is locally muted
 * @param selfStream Whether this user is stream using "Go Live".
 * @param selfVideo Whether this user's camera is enabled.
 * @param suppress Whether this user is muted by the current user.
 */
@Serializable
data class VoiceStateData(
    @SerialName("guild_id")
    val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
    @SerialName("channel_id")
    val channelId: Snowflake?,
    @SerialName("user_id")
    val userId: Snowflake,
    @SerialName("guild_member")
    val member: Optional<GuildMemberData> = Optional.Missing(),
    @SerialName("session_id")
    val sessionId: String,
    val deaf: Boolean,
    val mute: Boolean,
    @SerialName("self_deaf")
    val selfDeaf: Boolean,
    @SerialName("self_mute")
    val selfMute: Boolean,
    @SerialName("self_video")
    val selfVideo: Boolean,
    @SerialName("self_stream")
    val selfStream: OptionalBoolean = OptionalBoolean.Missing,
    val suppress: Boolean,
    @SerialName("request_to_speak_timestamp")
    val requestToSpeakTimestamp: String?
)
