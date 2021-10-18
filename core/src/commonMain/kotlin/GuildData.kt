package dev.kord.discord.objects

import dev.kord.discord.objects.gateway.payload.PresenceUpdate
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalInt
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * A representation of a [Discord Guild structure](https://discord.com/developers/docs/resources/guild#guild-object
 *
 * @param id The guild id.
 * @param name The guild name (2-100 characters, excluding trailing and leading whitespace)
 * @param icon The icon hash.
 * @param iconHash The icon hash, returned when in the template object.
 * @param splash The splash hash.
 * @param discoverySplash The discovery splash hash; only present for guilds with the [GuildFeature.Discoverable] feature.
 * @param owner True if [UserData] is the owner of the guild.
 * @param ownerId The id of the owner.
 * @param permissions The total permissions for [UserData] in the guild (excludes [overwrites][OverwriteData]).
 * @param region [VoiceRegionData] id for the guild.
 * @param afkChannelId The id of afk channel.
 * @param afkTimeout The afk timeout in seconds.
 * @param widgetEnabled True if the server widget is enabled.
 * @param widgetChannelId The channel id that the widget will generate an invite to, or `null` if set to no invite.
 * @param verificationLevel [VerificationLevel] required for the guild.
 * @param defaultMessageNotifications The [DefaultMessageNotificationLevel].
 * @param explicitContentFilter The [ExplicitContentFilter].
 * @param roles The roles in the guild.
 * @param emojis The custom guild emojis.
 * @param features The enabled guild features.
 * @param mfaLevel The required [MFALevel] for the guild.
 * @param applicationId The application id of the guild creator if it is bot-created.
 * @param systemChannelId The id of the channel where guild notices such as welcome messages and boost events are posted.
 * @param systemChannelFlags [SystemChannelFlags].
 * @param rulesChannelId The id of the channel where Community guilds can display rules and/or guidelines.
 * @param joinedAt When this guild was joined at.
 * @param large True if this is considered a large guild.
 * @param unavailable True if this guild is unavailable due to an outage.
 * @param memberCount The total number of members in this guild.
 * @param voiceStates The states of members currently in voice channels; lacks the [VoiceStateData.guildId] key.
 * @param members The users in the guild.
 * @param channels The channels in the guild.
 * @param presences The presences of the members in the guild, will only include non-offline members if the size is greater than `large threshold`.
 * @param maxPresences The maximum number of presences for the guild (the default value, currently 25000, is in effect when `null` is returned).
 * @param maxMembers The maximum number of members for the guild.
 * @param vanityUrlCode The vanity url code for the guild.
 * @param description The description for the guild, if the guild is discoverable.
 * @param banner The banner hash.
 * @param premiumTier The [PremiumTier] (Server Boost level).
 * @param premiumSubscriptionCount The number of boosts this guild currently has.
 * @param preferredLocale The preferred locale of a Community guild; used in server discovery and notices from Discord; defaults to "en-US".
 * @param publicUpdatesChannelId The id of the channel where admins and moderators of Community guilds receive notices from Discord.
 * @param maxVideoChannelUsers The maximum amount of users in a video channel.
 * @param approximateMemberCount The approximate number of members in this guild, returned from the `GET /guild/<id>` endpoint when `with_counts` is `true`.
 * @param approximatePresenceCount The approximate number of non-offline members in this guild, returned from the `GET /guild/<id>` endpoint when `with_counts` is `true`.
 * @param welcomeScreen The welcome screen of a Community guild, shown to new members.
 * @param nsfwLevel Guild NSFW level.
 */
@Serializable
data class GuildData(
    val id: Snowflake,
    val name: String,
    val icon: String?,
    @SerialName("icon_hash")
    val iconHash: Optional<String?> = Optional.Missing(),
    val splash: Optional<String?> = Optional.Missing(),
    @SerialName("discovery_splash")
    val discoverySplash: Optional<String?> = Optional.Missing(),
    val owner: OptionalBoolean = OptionalBoolean.Missing,
    @SerialName("owner_id")
    val ownerId: Snowflake,
    val permissions: Optional<Permissions> = Optional.Missing(),
    val region: Optional<String?> = Optional.Missing(),
    @SerialName("afk_channel_id")
    val afkChannelId: Snowflake?,
    @SerialName("afk_timeout")
    val afkTimeout: Int,
    @SerialName("widget_enabled")
    val widgetEnabled: OptionalBoolean = OptionalBoolean.Missing,
    @SerialName("widget_channel_id")
    val widgetChannelId: OptionalSnowflake? = OptionalSnowflake.Missing,
    @SerialName("verification_level")
    val verificationLevel: VerificationLevel,
    @SerialName("default_message_notifications")
    val defaultMessageNotifications: DefaultMessageNotificationLevel,
    @SerialName("explicit_content_filter")
    val explicitContentFilter: ExplicitContentFilter,
    val roles: List<RoleData>,
    val emojis: List<EmojiData>,
    val features: List<GuildFeature>,
    @SerialName("mfa_level")
    val mfaLevel: MFALevel,
    @SerialName("application_id")
    val applicationId: Snowflake?,
    @SerialName("system_channel_id")
    val systemChannelId: Snowflake?,
    @SerialName("system_channel_flags")
    val systemChannelFlags: SystemChannelFlags,
    @SerialName("rules_channel_id")
    val rulesChannelId: Snowflake?,
    @SerialName("joined_at")
    val joinedAt: Optional<String> = Optional.Missing(),
    val large: OptionalBoolean = OptionalBoolean.Missing,
    val unavailable: OptionalBoolean = OptionalBoolean.Missing,
    @SerialName("member_count")
    val memberCount: OptionalInt = OptionalInt.Missing,
    @SerialName("voice_states")
    val voiceStates: Optional<List<VoiceStateData>> = Optional.Missing(),
    val members: Optional<List<GuildMemberData>> = Optional.Missing(),
    val channels: Optional<List<ChannelData>> = Optional.Missing(),
    val threads: Optional<List<ChannelData>> = Optional.Missing(),
    val presences: Optional<List<PresenceUpdate.Data>> = Optional.Missing(),
    @SerialName("max_presences")
    val maxPresences: OptionalInt? = OptionalInt.Missing,
    @SerialName("max_members")
    val maxMembers: OptionalInt = OptionalInt.Missing,
    @SerialName("vanity_url_code")
    val vanityUrlCode: String?,
    val description: String?,
    val banner: String?,
    @SerialName("premium_tier")
    val premiumTier: PremiumTier,
    @SerialName("premium_subscription_count")
    val premiumSubscriptionCount: OptionalInt = OptionalInt.Missing,
    @SerialName("preferred_locale")
    val preferredLocale: String,
    @SerialName("public_updates_channel_id")
    val publicUpdatesChannelId: Snowflake?,
    @SerialName("max_video_channel_users")
    val maxVideoChannelUsers: OptionalInt = OptionalInt.Missing,
    @SerialName("approximate_member_count")
    val approximateMemberCount: OptionalInt = OptionalInt.Missing,
    @SerialName("approximate_presence_count")
    val approximatePresenceCount: OptionalInt = OptionalInt.Missing,
    @SerialName("welcome_screen")
    val welcomeScreen: Optional<WelcomeScreenData> = Optional.Missing(),
    @SerialName("nsfw_level")
    val nsfwLevel: NsfwLevel,
    @SerialName("stage_instances")
    val stageInstances: Optional<List<StageInstanceData>> = Optional.Missing(),
    val stickers: Optional<List<StickerData>> = Optional.Missing()
)

@Serializable(with = SystemChannelFlags.Companion::class)
class SystemChannelFlags constructor(val code: Int) {

    val flags = SystemChannelFlag.values().filter { contains(it) }

    operator fun contains(flag: SystemChannelFlag): Boolean {
        return this.code and flag.code == flag.code
    }

    override fun equals(other: Any?): Boolean {
        return (other as? SystemChannelFlags ?: return false).code == code
    }

    override fun hashCode(): Int {
        return code
    }

    override fun toString(): String = "SystemChannelFlags(flags=$flags)"

    companion object : KSerializer<SystemChannelFlags> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("system_channel_flags", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): SystemChannelFlags {
            return SystemChannelFlags(decoder.decodeInt())
        }

        override fun serialize(encoder: Encoder, value: SystemChannelFlags) {
            encoder.encodeInt(value.code)
        }
    }

}
