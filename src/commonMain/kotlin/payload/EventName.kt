package dev.kord.discord.objects.payload

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = EventName.Serializer::class)
sealed class EventName(val value: String) {

    override fun toString(): String = value

    class Unknown(value: String) : EventName(value)

    object ApplicationCommandCreate : EventName("APPLICATION_COMMAND_CREATE")
    object ApplicationCommandUpdate : EventName("APPLICATION_COMMAND_UPDATE")
    object ApplicationCommandDelete : EventName("APPLICATION_COMMAND_DELETE")

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

    object IntegrationsCreate : EventName("INTEGRATIONS_CREATE")
    object IntegrationsUpdate : EventName("INTEGRATIONS_UPDATE")
    object IntegrationsDelete : EventName("INTEGRATIONS_DELETE")

    object InteractionCreate : EventName("INTERACTION_CREATE")

    object InviteCreate : EventName("INVITE_CREATE")
    object InviteDelete : EventName("INVITE_DELETE")

    object MessageCreate : EventName("MESSAGE_CREATE")
    object MessageUpdate : EventName("MESSAGE_UPDATE")
    object MessageDelete : EventName("MESSAGE_DELETE")
    object MessageBulkDelete : EventName("MESSAGE_BULK_DELETE")
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

    companion object {

        operator fun invoke(value: String): EventName = values.firstOrNull { it.value == value } ?: Unknown(value)

        val values: Set<EventName>
            get() = setOf(
                ApplicationCommandCreate,
                ApplicationCommandDelete,
                ApplicationCommandUpdate,
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
                IntegrationsCreate,
                IntegrationsDelete,
                IntegrationsUpdate,
                InteractionCreate,
                InviteCreate,
                InviteDelete,
                MessageBulkDelete,
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
                TypingStart,
                UserUpdate,
                VoiceServerUpdate,
                VoiceStateUpdate,
                WebhooksUpdate,
            )
    }

    internal class Serializer : KSerializer<EventName> {

        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("t", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: EventName) {
            encoder.encodeString(value.value)
        }

        override fun deserialize(decoder: Decoder): EventName {
            return invoke(decoder.decodeString())
        }

    }

}
