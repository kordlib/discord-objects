package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * A representation of a [Discord Guild Feature](https://discord.com/developers/docs/resources/guild#guild-object-guild-features).
 */
@Serializable(with = GuildFeature.Serializer::class)
sealed class GuildFeature(val value: String) {

    override fun equals(other: Any?): Boolean {
        if (other !is GuildFeature) return false
        return other.value == value
    }

    override fun hashCode(): Int = value.hashCode()


    override fun toString(): String = "GuildFeature(value=$value)"

    class Unknown(value: String) : GuildFeature(value)

    /** Guild has access to set an invite splash background */
    object InviteSplash : GuildFeature("INVITE_SPLASH")

    /** Guild has access to set 384kbps bitrate in voice (previously VIP voice servers) */
    object VIPRegions : GuildFeature("VIP_REGIONS")

    /** Guild has access to set a vanity URL */
    object VanityUrl : GuildFeature("VANITY_URL")

    /** Guild is verified */
    object Verified : GuildFeature("VERIFIED")

    /** Guild is partnered */
    object Partnered : GuildFeature("PARTNERED")

    /** Guild can enable welcome screen and discovery, and receives community updates */
    object Community : GuildFeature("COMMUNITY")

    /** Guild has access to use commerce features (i.e. create store channels) */
    object Commerce : GuildFeature("COMMERCE")

    /** Guild has access to create news channels */
    object News : GuildFeature("NEWS")

    /** Guild is lurkable and able to be discovered directly */
    object Discoverable : GuildFeature("DISCOVERABLE")

    /** Guild is able to be featured in the directory */
    object Featurable : GuildFeature("FEATURABLE")

    /** Guild has access to set an animated guild icon */
    object AnimatedIcon : GuildFeature("ANIMATED_ICON")

    /** Guild has access to set a guild banner image */
    object Banner : GuildFeature("BANNER")

    /** Guild has enabled the welcome screen */
    object WelcomeScreenEnabled : GuildFeature("WELCOME_SCREEN_ENABLED")

    /** Guild has enabled ticketed events */
    object TicketedEventsEnabled : GuildFeature("TICKETED_EVENTS_ENABLED")

    /** Guild has enabled monetization */
    object MonetizationEnabled : GuildFeature("MONETIZATION_ENABLED")

    /** Guild has increased custom sticker slots */
    object MoreStickers : GuildFeature("MORE_STICKERS")

    /** Guild has access to the three-day archive time for threads */
    object ThreeDayThreadArchive : GuildFeature("THREE_DAY_THREAD_ARCHIVE")

    /** Guild has access to the seven day archive time for threads */
    object SevenDayThreadArchive : GuildFeature("SEVEN_DAY_THREAD_ARCHIVE")

    /** Guild has access to create private threads */
    object PrivateThreads : GuildFeature("PRIVATE_THREADS")

    internal object Serializer : KSerializer<GuildFeature> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("feature", PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): GuildFeature = when (val value = decoder.decodeString()) {
            "INVITE_SPLASH" -> InviteSplash
            "VIP_REGIONS" -> VIPRegions
            "VANITY_URL" -> VanityUrl
            "VERIFIED" -> Verified
            "PARTNERED" -> Partnered
            "COMMUNITY" -> Community
            "COMMERCE" -> Commerce
            "NEWS" -> News
            "DISCOVERABLE" -> Discoverable
            "FEATURABLE" -> Featurable
            "ANIMATED_ICON" -> AnimatedIcon
            "BANNER" -> Banner
            "WELCOME_SCREEN_ENABLED" -> WelcomeScreenEnabled
            "TICKETED_EVENTS_ENABLED" -> TicketedEventsEnabled
            "MONETIZATION_ENABLED" -> MonetizationEnabled
            "MORE_STICKERS" -> MoreStickers
            "THREE_DAY_THREAD_ARCHIVE" -> ThreeDayThreadArchive
            "SEVEN_DAY_THREAD_ARCHIVE" -> SevenDayThreadArchive
            "PRIVATE_THREADS" -> PrivateThreads
            else -> Unknown(value)
        }

        override fun serialize(encoder: Encoder, value: GuildFeature) {
            encoder.encodeString(value.value)
        }
    }
}
