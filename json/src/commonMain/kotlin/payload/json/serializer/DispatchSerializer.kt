package dev.kord.discord.objects.payload.json.serializer

import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.*
import kotlinx.serialization.ContextualSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.*

internal object DispatchSerializer : JsonContentPolymorphicSerializer<Dispatch<*>>(Dispatch::class) {

    @OptIn(ExperimentalSerializationApi::class)
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Dispatch<*>> {
        val json = element.jsonObject
        require(json["op"]?.jsonPrimitive?.int == Opcode.Dispatch.code) {
            "Dispatch Serializer expected opcode ${Opcode.Dispatch.code} but got ${json["op"]}"
        }

        return when (
            EventName(
                json["t"]?.jsonPrimitive?.content
                    ?: throw SerializationException("Dispatch Serializer expected event name but got ${json["t"]}")
            )
        ) {
            EventName.ChannelCreate -> ChannelCreate.serializer()
            EventName.ChannelDelete -> ChannelDelete.serializer()
            EventName.ChannelPinsUpdate -> ChannelPinsUpdate.serializer()
            EventName.ChannelUpdate -> ChannelUpdate.serializer()
            EventName.GuildBanAdd -> GuildBanAdd.serializer()
            EventName.GuildBanRemove -> GuildBanRemove.serializer()
            EventName.GuildCreate -> GuildCreate.serializer()
            EventName.GuildDelete -> GuildDelete.serializer()
            EventName.GuildEmojisUpdate -> GuildEmojisUpdate.serializer()
            EventName.GuildIntegrationsUpdate -> GuildIntegrationsUpdate.serializer()
            EventName.GuildMemberAdd -> GuildMemberAdd.serializer()
            EventName.GuildMemberRemove -> GuildMemberRemove.serializer()
            EventName.GuildMemberUpdate -> GuildMemberUpdate.serializer()
            EventName.GuildMembersChunk -> GuildMembersChunk.serializer()
            EventName.GuildRoleCreate -> GuildRoleCreate.serializer()
            EventName.GuildRoleDelete -> GuildRoleDelete.serializer()
            EventName.GuildRoleUpdate -> GuildRoleUpdate.serializer()
            EventName.GuildStickersUpdate -> GuildStickersUpdate.serializer()
            EventName.GuildUpdate -> GuildUpdate.serializer()
            EventName.IntegrationCreate -> IntegrationCreate.serializer()
            EventName.IntegrationDelete -> IntegrationDelete.serializer()
            EventName.IntegrationUpdate -> IntegrationUpdate.serializer()
            EventName.InteractionCreate -> InteractionCreate.serializer()
            EventName.InviteCreate -> InviteCreate.serializer()
            EventName.InviteDelete -> InviteDelete.serializer()
            EventName.MessageCreate -> MessageCreate.serializer()
            EventName.MessageDelete -> MessageDelete.serializer()
            EventName.MessageDeleteBulk -> MessageDeleteBulk.serializer()
            EventName.MessageReactionAdd -> MessageReactionAdd.serializer()
            EventName.MessageReactionRemove -> MessageReactionRemove.serializer()
            EventName.MessageReactionRemoveAll -> MessageReactionRemoveAll.serializer()
            EventName.MessageReactionRemoveEmoji -> MessageReactionRemoveEmoji.serializer()
            EventName.MessageUpdate -> MessageUpdate.serializer()
            EventName.PresenceUpdate -> PresenceUpdate.serializer()
            EventName.Ready -> Ready.serializer()
            EventName.Resumed -> Resumed.serializer()
            EventName.StageInstanceCreate -> StageInstanceCreate.serializer()
            EventName.StageInstanceDelete -> StageInstanceDelete.serializer()
            EventName.StageInstanceUpdate -> StageInstanceUpdate.serializer()
            EventName.ThreadCreate -> ThreadCreate.serializer()
            EventName.ThreadDelete -> ThreadDelete.serializer()
            EventName.ThreadListSync -> ThreadListSync.serializer()
            EventName.ThreadMemberUpdate -> ThreadMemberUpdate.serializer()
            EventName.ThreadMembersUpdate -> ThreadMembersUpdate.serializer()
            EventName.ThreadUpdate -> ThreadUpdate.serializer()
            EventName.TypingStart -> TypingStart.serializer()
            is EventName.Unknown -> ContextualSerializer(UnknownDispatch::class)
            EventName.UserUpdate -> UserUpdate.serializer()
            EventName.VoiceServerUpdate -> VoiceServerUpdate.serializer()
            EventName.VoiceStateUpdate -> VoiceStateUpdate.serializer()
            EventName.WebhooksUpdate -> WebhooksUpdate.serializer()
        }

    }

}
