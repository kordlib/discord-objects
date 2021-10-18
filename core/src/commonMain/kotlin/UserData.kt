package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

abstract class UserDataBase {
    abstract val id: Snowflake
    abstract val username: String
    abstract val discriminator: String
    abstract val avatar: String?
    abstract val bot: OptionalBoolean
    abstract val system: OptionalBoolean
    @SerialName("mfa_enabled")
    abstract val mfaEnabled: OptionalBoolean
    abstract val locale: Optional<String>
    abstract val verified: OptionalBoolean
    abstract val email: Optional<String?>
    abstract val flags: Optional<UserFlags>
    @SerialName("premium_type")
    abstract val premiumType: Optional<UserPremium>
    @SerialName("public_flags")
    abstract val publicFlags: Optional<UserFlags>
    abstract val banner: String?
    @SerialName("accent_color")
    abstract val accentColor: Int?
}

/**
 * A representation of the [Discord User structure](https://discord.com/developers/docs/resources/user).
 *
 * @param id The user's id.
 * @param username the user's username, not unique across the platform.
 * @param discriminator the 4-digit discord-tag.
 * @param avatar the user's avatar hash.
 * @param bot Whether the user belongs to an OAuth2 application.
 * @param system whether the user is an Official Discord System user (part of the urgent message system).
 * @param mfaEnabled Whether the user has two factor enabled on their account.
 * @param locale The user's chosen language option.
 * @param verified Whether the email on this account has been verified. Requires the `email` OAuth2 scope.
 * @param email The user's email. Requires the `email` OAuth2 scope.
 * @param flags The flags on a user's account. Unlike [publicFlags], these **are not** visible to other users.
 * @param premiumType The type of Nitro subscription on a user's account.
 * @param publicFlags The public flags on a user's account. Unlike [flags], these **are** visible ot other users.
 */
@Serializable
data class UserData(
    override val id: Snowflake,
    override val username: String,
    override val discriminator: String,
    override val avatar: String?,
    override val bot: OptionalBoolean = OptionalBoolean.Missing,
    override val system: OptionalBoolean = OptionalBoolean.Missing,
    @SerialName("mfa_enabled")
    override val mfaEnabled: OptionalBoolean = OptionalBoolean.Missing,
    override val locale: Optional<String> = Optional.Missing(),
    override val verified: OptionalBoolean = OptionalBoolean.Missing,
    override val email: Optional<String?> = Optional.Missing(),
    override val flags: Optional<UserFlags> = Optional.Missing(),
    @SerialName("premium_type")
    override val premiumType: Optional<UserPremium> = Optional.Missing(),
    @SerialName("public_flags")
    override val publicFlags: Optional<UserFlags> = Optional.Missing(),
    override val banner: String? = null,
    @SerialName("accent_color")
    override val accentColor: Int? = null
) : UserDataBase()

/**
 * A representation of the [Discord User structure](https://discord.com/developers/docs/resources/user).
 * This instance also contains a [member].
 *
 * @param id The user's id.
 * @param username the user's username, not unique across the platform.
 * @param discriminator the 4-digit discord-tag.
 * @param avatar the user's avatar hash.
 * @param bot Whether the user belongs to an OAuth2 application.
 * @param system whether the user is an Official Discord System user (part of the urgent message system).
 * @param mfaEnabled Whether the user has two factor enabled on their account.
 * @param locale The user's chosen language option.
 * @param verified Whether the email on this account has been verified. Requires the `email` OAuth2 scope.
 * @param email The user's email. Requires the `email` OAuth2 scope.
 * @param flags The flags on a user's account. Unlike [publicFlags], these **are not** visible to other users.
 * @param premiumType The type of Nitro subscription on a user's account.
 * @param publicFlags The public flags on a user's account. Unlike [flags], these **are** visible ot other users.
 */
@Serializable
data class OptionallyMemberUserData(
    override val id: Snowflake,
    override val username: String,
    override val discriminator: String,
    override val avatar: String?,
    override val bot: OptionalBoolean = OptionalBoolean.Missing,
    override val system: OptionalBoolean = OptionalBoolean.Missing,
    @SerialName("mfa_enabled")
    override val mfaEnabled: OptionalBoolean = OptionalBoolean.Missing,
    override val locale: Optional<String> = Optional.Missing(),
    override val verified: OptionalBoolean = OptionalBoolean.Missing,
    override val email: Optional<String?> = Optional.Missing(),
    override val flags: Optional<UserFlags> = Optional.Missing(),
    @SerialName("premium_type")
    override val premiumType: Optional<UserPremium> = Optional.Missing(),
    @SerialName("public_flags")
    override val publicFlags: Optional<UserFlags> = Optional.Missing(),
    override val banner: String? = null,
    @SerialName("accent_color")
    override val accentColor: Int? = null,
    val member: Optional<GuildMemberData> = Optional.Missing(),
) : UserDataBase()
