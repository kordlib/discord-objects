package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.api.EnumType
import dev.kord.discord.objects.api.EnumTypeCompanion
import dev.kord.discord.objects.api.serializer.StringEnumTypeSerializer
import kotlinx.serialization.Serializable

@Serializable(with = EventName.Serializer::class)
sealed class EventName(override val value: String) : EnumType<String> {

    override fun toString(): String = value

    class Unknown(value: String) : EventName(value)

    object ChannelCreate : EventName("CHANNEL_CREATE")
    object ChannelUpdate : EventName("CHANNEL_UPDATE")
    object ChannelDelete : EventName("CHANNEL_DELETE")
    object ChannelPinsUpdate : EventName("CHANNEL_PINS_UPDATE")

    object ThreadCreate : EventName("THREAD_CREATE")
    object ThreadUpdate : EventName("THREAD_UPDATE")
    object ThreadDelete : EventName("THREAD_DELETE")
    object ThreadListSync : EventName("THREAD_LIST_SYNC")
    object ThreadMemberUpdate : EventName("THREAD_MEMBER_UPDATE")
    object ThreadMembersUpdate : EventName("THREAD_MEMBERS_UPDATE")

    object GuildCreate : EventName("GUILD_CREATE")
    object GuildUpdate : EventName("GUILD_UPDATE")
    object GuildDelete : EventName("GUILD_DELETE")
    object GuildBanAdd : EventName("GUILD_BAN_ADD")
    object GuildBanRemove : EventName("GUILD_BAN_REMOVE")
    object GuildEmojisUpdate : EventName("GUILD_EMOJIS_UPDATE")
    object GuildStickersUpdate : EventName("GUILD_STICKERS_UPDATE")
    object GuildIntegrationsUpdate : EventName("GUILD_INTEGRATIONS_UPDATE")
    object GuildMemberAdd : EventName("GUILD_MEMBER_ADD")
    object GuildMemberRemove : EventName("GUILD_MEMBER_REMOVE")
    object GuildMemberUpdate : EventName("GUILD_MEMBER_UPDATE")
    object GuildMembersChunk : EventName("GUILD_MEMBERS_CHUNK")
    object GuildRoleCreate : EventName("GUILD_ROLE_CREATE")
    object GuildRoleUpdate : EventName("GUILD_ROLE_UPDATE")
    object GuildRoleDelete : EventName("GUILD_ROLE_DELETE")

    object IntegrationCreate : EventName("INTEGRATION_CREATE")
    object IntegrationUpdate : EventName("INTEGRATION_UPDATE")
    object IntegrationDelete : EventName("INTEGRATION_DELETE")

    object InteractionCreate : EventName("INTERACTION_CREATE")

    object InviteCreate : EventName("INVITE_CREATE")
    object InviteDelete : EventName("INVITE_DELETE")

    object MessageCreate : EventName("MESSAGE_CREATE")
    object MessageUpdate : EventName("MESSAGE_UPDATE")
    object MessageDelete : EventName("MESSAGE_DELETE")
    object MessageDeleteBulk : EventName("MESSAGE_DELETE_BULK")
    object MessageReactionAdd : EventName("MESSAGE_REACTION_ADD")
    object MessageReactionRemove : EventName("MESSAGE_REACTION_REMOVE")
    object MessageReactionRemoveAll : EventName("MESSAGE_REACTION_REMOVE_ALL")
    object MessageReactionRemoveEmoji : EventName("MESSAGE_REACTION_REMOVE_EMOJI")

    object PresenceUpdate : EventName("PRESENCE_UPDATE")

    object Ready : EventName("READY")
    object Resumed : EventName("RESUMED")

    object StageInstanceCreate : EventName("STAGE_INSTANCE_CREATE")
    object StageInstanceUpdate : EventName("STAGE_INSTANCE_UPDATE")
    object StageInstanceDelete : EventName("STAGE_INSTANCE_DELETE")

    object TypingStart : EventName("TYPING_START")

    object UserUpdate : EventName("USER_UPDATE")

    object VoiceStateUpdate : EventName("VOICE_STATE_UPDATE")
    object VoiceServerUpdate : EventName("VOICE_SERVER_UPDATE")

    object WebhooksUpdate : EventName("WEBHOOKS_UPDATE")

    companion object : EnumTypeCompanion<EventName> {

        operator fun invoke(value: String): EventName = values.firstOrNull { it.value == value } ?: Unknown(value)

        override val values: Set<EventName>
            get() = setOf(
                ChannelCreate,
                ChannelDelete,
                ChannelPinsUpdate,
                ChannelUpdate,
                GuildBanAdd,
                GuildCreate,
                GuildDelete,
                GuildUpdate,
                ThreadCreate,
                ThreadDelete,
                ThreadListSync,
                ThreadMemberUpdate,
                ThreadMembersUpdate,
                ThreadUpdate,
                GuildBanRemove,
                GuildEmojisUpdate,
                GuildIntegrationsUpdate,
                GuildMemberAdd,
                GuildMemberRemove,
                GuildMemberUpdate,
                GuildMembersChunk,
                GuildRoleCreate,
                GuildRoleDelete,
                GuildRoleUpdate,
                GuildStickersUpdate,
                IntegrationCreate,
                IntegrationDelete,
                IntegrationUpdate,
                InteractionCreate,
                InviteCreate,
                InviteDelete,
                MessageDeleteBulk,
                MessageCreate,
                MessageDelete,
                MessageReactionAdd,
                MessageReactionRemove,
                MessageReactionRemoveAll,
                MessageReactionRemoveEmoji,
                MessageUpdate,
                PresenceUpdate,
                StageInstanceCreate,
                StageInstanceDelete,
                StageInstanceUpdate,
                Ready,
                Resumed,
                TypingStart,
                UserUpdate,
                VoiceServerUpdate,
                VoiceStateUpdate,
                WebhooksUpdate,
            )
    }

    internal object Serializer : StringEnumTypeSerializer<EventName>(
        "EventName", values, ::Unknown
    )

}
