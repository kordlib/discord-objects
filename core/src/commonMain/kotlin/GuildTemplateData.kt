package dev.kord.discord.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildTemplateData(
    val code: String,
    val name: String,
    val description: String?,
    @SerialName("usage_count")
    val usageCount: Int,
    @SerialName("creator_id")
    val creatorId: Snowflake,
    val creator: UserData,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("source_guild_id")
    val sourceGuildId: Snowflake,
    @SerialName("serialized_source_guild")
    val serializedSourceGuild: PartialGuildData,
    @SerialName("is_dirty")
    val isDirty: Boolean?
)
