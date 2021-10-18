package dev.kord.discord.objects

import kotlinx.serialization.Serializable

@Serializable
data class PartialGuildApplicationCommandPermissionsData(
    val id: Snowflake,
    val permissions: List<GuildApplicationCommandPermissionData>
)
