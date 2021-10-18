package dev.kord.discord.objects

import dev.kord.discord.objects.optional.OptionalBoolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllowedMentionsData(
    val parse: List<AllowedMentionType>,
    val users: List<Snowflake>,
    val roles: List<Snowflake>,
    @SerialName("replied_user")
    val repliedUser: OptionalBoolean = OptionalBoolean.Missing
)
