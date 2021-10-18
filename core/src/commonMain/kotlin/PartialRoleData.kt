package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalInt
import kotlinx.serialization.Serializable

@Serializable
data class PartialRoleData(
    val id: Snowflake,
    val name: Optional<String> = Optional.Missing(),
    val color: OptionalInt = OptionalInt.Missing,
    val hoist: OptionalBoolean = OptionalBoolean.Missing,
    val position: OptionalInt = OptionalInt.Missing,
    val permissions: Optional<Permissions> = Optional.Missing(),
    val managed: OptionalBoolean = OptionalBoolean.Missing,
    val mentionable: OptionalBoolean = OptionalBoolean.Missing,
    val tags: Optional<RoleTagsData> = Optional.Missing(),
)
