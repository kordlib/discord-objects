package dev.kord.discord.objects

/**
 * A representation of a [Discord Channels Flag](https://discord.com/developers/docs/resources/guild#guild-object-system-channel-flags).
 */
enum class SystemChannelFlag(val code: Int) {
    /** Suppress member join notifications. **/
    SuppressJoinNotifications(1.shl(0)),

    /** Suppress server boost notifications. **/
    SuppressPremiumSubscriptions(1.shl(1))
}
