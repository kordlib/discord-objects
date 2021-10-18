package dev.kord.discord.objects

import kotlinx.serialization.Serializable

@Serializable
data class GuildApplicationCommandPermissionData(
    val id: Snowflake,
    val type: GuildApplicationCommandPermissionDataType,
    val permission: Boolean
)
