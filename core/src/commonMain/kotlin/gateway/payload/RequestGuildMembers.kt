package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.*
import dev.kord.discord.objects.gateway.payload.RequestGuildMembers.Data
import dev.kord.discord.objects.gateway.payload.serializer.CommandSerializer
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalInt
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A representation of the
 * [Discord Request Guild Members command](https://discord.com/developers/docs/topics/gateway#request-guild-members).
 *
 * When connecting to a Gateway Discord will send members up to [Identify.Data.largeThreshold], any additional
 * members can be requested via this command. Sending this command will result in a variable amount of
 * [GuildMembersChunk] events being send until all requested members have been returned.
 *
 * While usage of this command isn't strictly limited to [privileged intents][PrivilegedIntent],
 * certain combinations are:
 * - [Intent.GuildPresences] is required to enable [Data.presences].
 * - [Intent.GuildMembers] is required when setting the [Data.query] to `""` and [Data.limit] to `0`.
 *
 * Other notable behavior:
 * - Requesting a [Data.query] that is not empty (and not [Optional.Missing]) will coerce [Data.limit] to a max of `100`.
 * - [Data.userIds] can only contain a maximum of `100` ids.
 *
 */
@Serializable(with = RequestGuildMembers.Serializer::class)
data class RequestGuildMembers(
    override val data: Data
) : Command<Data> {

    override val opcode: Opcode
        get() = Opcode.RequestGuildMembers

    /**
     * @param guildId id of the guild on which to execute the command.
     * @param query prefix to match usernames against. Use an empty string to match against all members.
     * @param limit maximum number of members to match against when using a [Data.query]. Use `0` to request all members.
     * @param presences Whether [GuildMembersChunkData.presences] should be present in the response.
     * @param userIds The ids of the user to match against.
     * @param nonce A nonce to identify the [GuildMembersChunkData.nonce] responses.
     */
    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
        val query: Optional<String> = Optional.Missing(),
        val limit: OptionalInt = OptionalInt.Missing,
        val presences: OptionalBoolean = OptionalBoolean.Missing,
        @SerialName("user_ids")
        val userIds: Optional<Set<Snowflake>> = Optional.Missing(),
        val nonce: Optional<String> = Optional.Missing()
    )

    internal object Serializer : KSerializer<RequestGuildMembers> by CommandSerializer(::RequestGuildMembers)

}
