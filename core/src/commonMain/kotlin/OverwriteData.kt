package dev.kord.discord.objects

import kotlinx.serialization.Serializable

@Serializable
data class OverwriteData(
    val id: Snowflake,
    val type: OverwriteType,
    val allow: Permissions,
    val deny: Permissions,
) {
    companion object
}
