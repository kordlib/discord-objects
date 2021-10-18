package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A partial representation of a [Discord Guild structure](https://discord.com/developers/docs/resources/guild#guild-object
 *
 * @param id The guild id.
 * @param name The guild name (2-100 characters, excluding trailing and leading whitespace)
 * @param icon The icon hash.
 * @param owner True if [UserData] is the owner of the guild.
 * @param features The enabled guild features.
 */
@Serializable
class PartialGuildData(
    val id: Snowflake,
    val name: String,
    val icon: String?,
    val owner: OptionalBoolean = OptionalBoolean.Missing,
    val permissions: Optional<Permissions> = Optional.Missing(),
    val features: List<GuildFeature>,
    @SerialName("welcome_screen")
    val welcomeScreen: Optional<WelcomeScreenData> = Optional.Missing()
)
