package dev.kord.discord.objects

import dev.kord.discord.objects.optional.OptionalBoolean
import kotlinx.serialization.Serializable

/**
 * A partial representation of a [GuildData] that may be [unavailable].
 *
 * @param id the id of the Guild.
 * @param unavailable Whether the Guild is unavailable. Contains a value on true.
 */
@Serializable
data class UnavailableGuildData(
    val id: Snowflake,
    val unavailable: OptionalBoolean = OptionalBoolean.Missing,
)
