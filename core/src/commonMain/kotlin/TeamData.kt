package dev.kord.discord.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The raw developer team data gotten from the API.
 */
@Serializable
data class TeamData(
    val icon: String?,
    val id: Snowflake,
    val members: List<TeamMemberData>,
    @SerialName("owner_user_id")
    val ownerUserId: Snowflake,
)
