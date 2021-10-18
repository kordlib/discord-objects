package dev.kord.discord.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A representation of a [Discord Channel Mention structure](https://discord.com/developers/docs/resources/channel#channel-mention-object-channel-mention-structure).
 *
 * @param id The id of the channel.
 * @param guildId The id of the guild containing the channel.
 * @param type The type of channel.
 * @param name the name of the channel.
 */
@Serializable
data class MentionedChannelData(
    val id: Snowflake,
    @SerialName("guild_id")
    val guildId: Snowflake,
    val type: ChannelType,
    val name: String,
)
