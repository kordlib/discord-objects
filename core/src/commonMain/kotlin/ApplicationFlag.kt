package dev.kord.discord.objects

import dev.kord.discord.objects.api.DiscordBitSet

sealed class ApplicationFlag(val code: DiscordBitSet) {
    constructor(code: Long) : this(DiscordBitSet(code))

    /**
     * An ApplicationFlag unknown to this version of Kord.
     */
    class Unknown(code: DiscordBitSet) : ApplicationFlag(code) {
        constructor(code: Long) : this(DiscordBitSet(code))
    }

    object GatewayPresence : ApplicationFlag(1L shl 12)

    object GatewayPresenceLimited : ApplicationFlag(1L shl 13)

    object GatewayGuildMembers : ApplicationFlag(1L shl 14)

    object GatewayGuildMembersLimited : ApplicationFlag(1L shl 15)

    object VerificationPendingGuildLimited : ApplicationFlag(1L shl 16)

    object Embedded : ApplicationFlag(1L shl 17)

    companion object {
        val values : Set<ApplicationFlag> get() = setOf(
            GatewayPresence,
            GatewayPresenceLimited,
            GatewayGuildMembers,
            GatewayPresenceLimited,
            VerificationPendingGuildLimited,
            Embedded
        )
    }
}
