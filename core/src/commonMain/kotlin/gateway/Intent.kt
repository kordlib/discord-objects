package dev.kord.discord.objects.gateway

import dev.kord.discord.objects.api.DiscordBitSet
import dev.kord.discord.objects.gateway.payload.*

/**
 * Values that enable a group of events as [defined by Discord](https://github.com/discord/discord-api-docs/blob/feature/gateway-intents/docs/topics/Gateway.md#gateway-intents).
 */
sealed class Intent(val code: DiscordBitSet) {
    constructor(code: Long) : this(DiscordBitSet(code))

    /**
     * An Intent unknown to this version of Kord.
     */
    class Unknown(code: DiscordBitSet) : Intent(code) {
        constructor(code: Long) : this(DiscordBitSet(code))
    }

    /**
     * Enables the following events:
     * - [GuildCreate]
     * - [GuildDelete]
     * - [GuildRoleCreate]
     * - [GuildRoleUpdate]
     * - [GuildRoleDelete]
     * - [ChannelCreate]
     * - [ChannelUpdate]
     * - [ChannelDelete]
     * - [ChannelPinsUpdate]
     */
    object Guilds : Intent(1L shl 0)

    /**
     * Enables the following events:
     * - [GuildMemberAdd]
     * - [GuildMemberUpdate]
     * - [GuildMemberRemove]
     */
    @PrivilegedIntent
    object GuildMembers : Intent(1L shl 1)

    /**
     * Enables the following events:
     * - [GuildBanAdd]
     * - [GuildBanRemove]
     */
    object GuildBans : Intent(1L shl 2)

    /**
     * Enables the following events:
     * - [GuildEmojisUpdate]
     */
    object GuildEmojis : Intent(1L shl 3)

    /**
     * Enables the following events:
     * - [GuildIntegrationsUpdate]
     */
    object GuildIntegrations : Intent(1L shl 4)

    /**
     * Enables the following events:
     * - [WebhooksUpdate]
     */
    object GuildWebhooks : Intent(1L shl 5)

    /**
     * Enables the following events:
     * - INVITE_CREATE
     * - INVITE_DELETE
     */
    object GuildInvites : Intent(1L shl 6)

    /**
     * Enables the following events:
     * - [UpdateVoiceState]
     */
    object GuildVoiceStates : Intent(1L shl 7)

    /**
     * Enables the following events:
     * - [PresenceUpdate]
     */
    @PrivilegedIntent
    object GuildPresences : Intent(1L shl 8)

    /**
     * Enables the following events:
     * - [MessageCreate]
     * - [MessageUpdate]
     * - [MessageDelete]
     * - [MessageDeleteBulk]
     */
    object GuildMessages : Intent(1L shl 9)

    /**
     * Enables the following events:
     * - [MessageReactionAdd]
     * - [MessageReactionRemove]
     * - [MessageReactionRemoveAll]
     * - MESSAGE_REACTION_REMOVE_EMOJI
     */
    object GuildMessageReactions : Intent(1L shl 10)

    /**
     * Enables the following events:
     * - [TypingStart]
     */
    object GuildMessageTyping : Intent(1L shl 11)

    /**
     * Enables the following events:
     * - [ChannelCreate]
     * - [ChannelDelete]
     * - [MessageUpdate]
     * - [MessageDelete]
     */
    object DirectMessages : Intent(1L shl 12)

    /**
     * Enables the following events:
     * - [MessageReactionAdd]
     * - [MessageReactionRemove]
     * - [MessageReactionRemoveAll]
     * - MESSAGE_REACTION_REMOVE_EMOJI
     */
    object DirectMessagesReactions : Intent(1L shl 13)

    /**
     * Enables the following events:
     * - [TypingStart]
     */
    object DirectMessageTyping : Intent(1L shl 14)

    companion object {
        @OptIn(PrivilegedIntent::class)
        val values: Set<Intent>
            get() = setOf(
                DirectMessageTyping,
                GuildIntegrations,
                GuildEmojis,
                DirectMessageTyping,
                DirectMessages,
                DirectMessagesReactions,
                GuildBans,
                Guilds,
                GuildVoiceStates,
                GuildMessages,
                GuildMessageReactions,
                GuildWebhooks,
                GuildInvites,
                GuildPresences,
                GuildMembers
            )
    }
}
