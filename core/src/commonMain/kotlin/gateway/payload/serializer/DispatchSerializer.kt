@file:Suppress("UNCHECKED_CAST")

package dev.kord.discord.objects.gateway.payload.serializer

import dev.kord.discord.objects.*
import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.*
import kotlinx.serialization.ContextualSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure

/**
 * Serializer for Dispatch Payloads.
 *
 * Because kx.ser-core only allows in-order serialization, this serializer comes with the limitation that
 * the `d` (data) field must always be the last field in a payload, and must be preceded by the `t` (event name)
 * and `op` (opcode) field. This allows for the information needed to figure out the type of event being handled.
 */
@OptIn(ExperimentalSerializationApi::class)
internal class DispatchSerializer<T>(@Suppress("UNUSED_PARAMETER") ignored: KSerializer<T>) : KSerializer<Dispatch<T>> {

    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("dev.kord.discord.objects.gateway.payload.Dispatch") {
            element("t", EventName.serializer().descriptor, isOptional = false)
            element("s", Int.serializer().descriptor, isOptional = false)
            element("op", Opcode.serializer().descriptor, isOptional = false)
            element("d", Unit.serializer().descriptor, isOptional = true)
        }

    override fun deserialize(decoder: Decoder): Dispatch<T> = decoder.decodeStructure(descriptor) {
        var name: EventName? = null
        var sequence: Int? = null
        var opcode: Opcode? = null

        var dispatch: Dispatch<*>? = null

        while (true) {
            when (val index = decodeElementIndex(descriptor)) {
                0 -> {
                    name = decodeSerializableElement(descriptor, index, EventName.serializer())
                    require(name !is EventName.Unknown) {
                        "Unknown event name: $name cannot be deserialized"
                    }
                }
                1 -> sequence = decodeIntElement(descriptor, index)
                2 -> {
                    opcode = decodeSerializableElement(descriptor, index, Opcode.serializer())
                    if(opcode !is Opcode.Dispatch) {
                        throw SerializationException("Opcode $opcode is not a Dispatch opcode.")
                    }
                }
                3 -> {
                    requireNotNull(name) { "'t' field is required before 'd' field" }
                    requireNotNull(sequence) { "'s' field is required before 'd' field" }
                    requireNotNull(opcode) { "'op' field is required before 'd' field" }
                    dispatch = decodeDispatchAtDataIndex(descriptor, index, name, sequence)
                }
                CompositeDecoder.DECODE_DONE -> break
            }
        }

        @Suppress("UNCHECKED_CAST")
        return when {
            dispatch != null -> dispatch
            name == EventName.Resumed -> {
                requireNotNull(sequence) { "'s' field is required for Dispatch Payloads" }
                Resumed(sequence)
            }
            else -> throw SerializationException("'d' field is required for Dispatch Payloads")
        } as Dispatch<T>
    }

    override fun serialize(encoder: Encoder, value: Dispatch<T>) = when (value) {
        is ChannelCreate -> ChannelCreate.serializer().serialize(encoder, value)
        is ChannelDelete -> ChannelDelete.serializer().serialize(encoder, value)
        is ChannelPinsUpdate -> ChannelPinsUpdate.serializer().serialize(encoder, value)
        is ChannelUpdate -> ChannelUpdate.serializer().serialize(encoder, value)
        is GuildBanAdd -> GuildBanAdd.serializer().serialize(encoder, value)
        is GuildBanRemove -> GuildBanRemove.serializer().serialize(encoder, value)
        is GuildCreate -> GuildCreate.serializer().serialize(encoder, value)
        is GuildDelete -> GuildDelete.serializer().serialize(encoder, value)
        is GuildEmojisUpdate -> GuildEmojisUpdate.serializer().serialize(encoder, value)
        is GuildIntegrationsUpdate -> GuildIntegrationsUpdate.serializer().serialize(encoder, value)
        is GuildMemberAdd -> GuildMemberAdd.serializer().serialize(encoder, value)
        is GuildMemberRemove -> GuildMemberRemove.serializer().serialize(encoder, value)
        is GuildMemberUpdate -> GuildMemberUpdate.serializer().serialize(encoder, value)
        is GuildMembersChunk -> GuildMembersChunk.serializer().serialize(encoder, value)
        is GuildRoleCreate -> GuildRoleCreate.serializer().serialize(encoder, value)
        is GuildRoleDelete -> GuildRoleDelete.serializer().serialize(encoder, value)
        is GuildRoleUpdate -> GuildRoleUpdate.serializer().serialize(encoder, value)
        is GuildStickersUpdate -> GuildStickersUpdate.serializer().serialize(encoder, value)
        is GuildUpdate -> GuildUpdate.serializer().serialize(encoder, value)
        is IntegrationCreate -> IntegrationCreate.serializer().serialize(encoder, value)
        is IntegrationDelete -> IntegrationDelete.serializer().serialize(encoder, value)
        is IntegrationUpdate -> IntegrationUpdate.serializer().serialize(encoder, value)
        is InteractionCreate -> InteractionCreate.serializer().serialize(encoder, value)
        is InviteCreate -> InviteCreate.serializer().serialize(encoder, value)
        is InviteDelete -> InviteDelete.serializer().serialize(encoder, value)
        is MessageCreate -> MessageCreate.serializer().serialize(encoder, value)
        is MessageDelete -> MessageDelete.serializer().serialize(encoder, value)
        is MessageDeleteBulk -> MessageDeleteBulk.serializer().serialize(encoder, value)
        is MessageReactionAdd -> MessageReactionAdd.serializer().serialize(encoder, value)
        is MessageReactionRemove -> MessageReactionRemove.serializer().serialize(encoder, value)
        is MessageReactionRemoveAll -> MessageReactionRemoveAll.serializer().serialize(encoder, value)
        is MessageReactionRemoveEmoji -> MessageReactionRemoveEmoji.serializer().serialize(encoder, value)
        is MessageUpdate -> MessageUpdate.serializer().serialize(encoder, value)
        is PresenceUpdate -> PresenceUpdate.serializer().serialize(encoder, value)
        is Ready -> Ready.serializer().serialize(encoder, value)
        is Resumed -> Resumed.serializer().serialize(encoder, value)
        is StageInstanceCreate -> StageInstanceCreate.serializer().serialize(encoder, value)
        is StageInstanceDelete -> StageInstanceDelete.serializer().serialize(encoder, value)
        is StageInstanceUpdate -> StageInstanceUpdate.serializer().serialize(encoder, value)
        is ThreadCreate -> ThreadCreate.serializer().serialize(encoder, value)
        is ThreadDelete -> ThreadDelete.serializer().serialize(encoder, value)
        is ThreadListSync -> ThreadListSync.serializer().serialize(encoder, value)
        is ThreadMemberUpdate -> ThreadMemberUpdate.serializer().serialize(encoder, value)
        is ThreadMembersUpdate -> ThreadMembersUpdate.serializer().serialize(encoder, value)
        is ThreadUpdate -> ThreadUpdate.serializer().serialize(encoder, value)
        is TypingStart -> TypingStart.serializer().serialize(encoder, value)
        is UnknownDispatch -> ContextualSerializer(UnknownDispatch::class).serialize(encoder, value)
        is UserUpdate -> UserUpdate.serializer().serialize(encoder, value)
        is VoiceServerUpdate -> VoiceServerUpdate.serializer().serialize(encoder, value)
        is VoiceStateUpdate -> VoiceStateUpdate.serializer().serialize(encoder, value)
        is WebhooksUpdate -> WebhooksUpdate.serializer().serialize(encoder, value)
    }

    companion object {
        internal fun CompositeDecoder.decodeDispatchAtDataIndex(
            descriptor: SerialDescriptor,
            index: Int,
            name: EventName,
            sequence: Int
        ): Dispatch<*> = when (name) {
            EventName.ChannelCreate -> ChannelCreate(
                decodeSerializableElement(descriptor, index, ChannelData.serializer()),
                sequence
            )
            EventName.ChannelDelete -> ChannelDelete(
                decodeSerializableElement(descriptor, index, ChannelData.serializer()),
                sequence
            )
            EventName.ChannelPinsUpdate -> ChannelPinsUpdate(
                decodeSerializableElement(descriptor, index, ChannelPinsUpdate.Data.serializer()),
                sequence
            )
            EventName.ChannelUpdate -> ChannelUpdate(
                decodeSerializableElement(descriptor, index, ChannelData.serializer()),
                sequence
            )
            EventName.GuildBanAdd -> GuildBanAdd(
                decodeSerializableElement(descriptor, index, GuildBanAdd.Data.serializer()),
                sequence
            )
            EventName.GuildBanRemove -> GuildBanRemove(
                decodeSerializableElement(descriptor, index, GuildBanRemove.Data.serializer()),
                sequence
            )
            EventName.GuildCreate -> GuildCreate(
                decodeSerializableElement(descriptor, index, GuildData.serializer()),
                sequence
            )
            EventName.GuildDelete -> GuildDelete(
                decodeSerializableElement(descriptor, index, UnavailableGuildData.serializer()),
                sequence
            )
            EventName.GuildEmojisUpdate -> GuildEmojisUpdate(
                decodeSerializableElement(descriptor, index, GuildEmojisUpdate.Data.serializer()),
                sequence
            )
            EventName.GuildIntegrationsUpdate -> GuildIntegrationsUpdate(
                decodeSerializableElement(descriptor, index, GuildIntegrationsUpdate.Data.serializer()),
                sequence
            )
            EventName.GuildMemberAdd -> GuildMemberAdd(
                decodeSerializableElement(descriptor, index, GuildMemberAdd.Data.serializer()),
                sequence
            )
            EventName.GuildMemberRemove -> GuildMemberRemove(
                decodeSerializableElement(descriptor, index, GuildMemberRemove.Data.serializer()),
                sequence
            )
            EventName.GuildMemberUpdate -> GuildMemberUpdate(
                decodeSerializableElement(descriptor, index, GuildMemberUpdate.Data.serializer()),
                sequence
            )
            EventName.GuildMembersChunk -> GuildMembersChunk(
                decodeSerializableElement(descriptor, index, GuildMembersChunk.Data.serializer()),
                sequence
            )
            EventName.GuildRoleCreate -> GuildRoleCreate(
                decodeSerializableElement(descriptor, index, GuildRoleCreate.Data.serializer()),
                sequence
            )
            EventName.GuildRoleDelete -> GuildRoleDelete(
                decodeSerializableElement(descriptor, index, GuildRoleDelete.Data.serializer()),
                sequence
            )
            EventName.GuildRoleUpdate -> GuildRoleUpdate(
                decodeSerializableElement(descriptor, index, GuildRoleUpdate.Data.serializer()),
                sequence
            )
            EventName.GuildStickersUpdate -> GuildStickersUpdate(
                decodeSerializableElement(descriptor, index, GuildStickersUpdate.Data.serializer()),
                sequence
            )
            EventName.GuildUpdate -> GuildUpdate(
                decodeSerializableElement(descriptor, index, GuildData.serializer()),
                sequence
            )
            EventName.IntegrationCreate -> IntegrationCreate(
                decodeSerializableElement(descriptor, index, IntegrationData.serializer()),
                sequence
            )
            EventName.IntegrationDelete -> IntegrationDelete(
                decodeSerializableElement(descriptor, index, IntegrationDelete.Data.serializer()),
                sequence
            )
            EventName.IntegrationUpdate -> IntegrationUpdate(
                decodeSerializableElement(descriptor, index, IntegrationUpdate.Data.serializer()),
                sequence
            )
            EventName.InteractionCreate -> InteractionCreate(
                decodeSerializableElement(descriptor, index, InteractionData.serializer()),
                sequence
            )
            EventName.InviteCreate -> InviteCreate(
                decodeSerializableElement(descriptor, index, InviteCreate.Data.serializer()),
                sequence
            )
            EventName.InviteDelete -> InviteDelete(
                decodeSerializableElement(descriptor, index, InviteDelete.Data.serializer()),
                sequence
            )
            EventName.MessageCreate -> MessageCreate(
                decodeSerializableElement(descriptor, index, MessageData.serializer()),
                sequence
            )
            EventName.MessageDelete -> MessageDelete(
                decodeSerializableElement(descriptor, index, MessageDelete.Data.serializer()),
                sequence
            )
            EventName.MessageDeleteBulk -> MessageDeleteBulk(
                decodeSerializableElement(descriptor, index, MessageDeleteBulk.Data.serializer()),
                sequence
            )
            EventName.MessageReactionAdd -> MessageReactionAdd(
                decodeSerializableElement(descriptor, index, MessageReactionAdd.Data.serializer()),
                sequence
            )
            EventName.MessageReactionRemove -> MessageReactionRemove(
                decodeSerializableElement(descriptor, index, MessageReactionRemove.Data.serializer()),
                sequence
            )
            EventName.MessageReactionRemoveAll -> MessageReactionRemoveAll(
                decodeSerializableElement(descriptor, index, MessageReactionRemoveAll.Data.serializer()),
                sequence
            )
            EventName.MessageReactionRemoveEmoji -> MessageReactionRemoveEmoji(
                decodeSerializableElement(descriptor, index, MessageReactionRemoveEmoji.Data.serializer()),
                sequence
            )
            EventName.MessageUpdate -> MessageUpdate(
                decodeSerializableElement(descriptor, index, PartialMessageData.serializer()),
                sequence
            )
            EventName.PresenceUpdate -> PresenceUpdate(
                decodeSerializableElement(descriptor, index, PresenceUpdate.Data.serializer()),
                sequence
            )
            EventName.Ready -> Ready(
                decodeSerializableElement(descriptor, index, Ready.Data.serializer()),
                sequence
            )
            EventName.Resumed -> {
                //decode null
                decodeNullableSerializableElement(descriptor, index, Unit.serializer().nullable)
                Resumed(sequence)
            }
            EventName.StageInstanceCreate -> StageInstanceCreate(
                decodeSerializableElement(descriptor, index, StageInstanceData.serializer()),
                sequence
            )
            EventName.StageInstanceDelete -> StageInstanceDelete(
                decodeSerializableElement(descriptor, index, StageInstanceData.serializer()),
                sequence
            )
            EventName.StageInstanceUpdate -> StageInstanceUpdate(
                decodeSerializableElement(descriptor, index, StageInstanceData.serializer()),
                sequence
            )
            EventName.ThreadCreate -> ThreadCreate(
                decodeSerializableElement(descriptor, index, ChannelData.serializer()),
                sequence
            )
            EventName.ThreadDelete -> ThreadDelete(
                decodeSerializableElement(descriptor, index, ChannelData.serializer()),
                sequence
            )
            EventName.ThreadListSync -> ThreadListSync(
                decodeSerializableElement(descriptor, index, ThreadListSync.Data.serializer()),
                sequence
            )
            EventName.ThreadMemberUpdate -> ThreadMemberUpdate(
                decodeSerializableElement(descriptor, index, ThreadMemberData.serializer()),
                sequence
            )
            EventName.ThreadMembersUpdate -> ThreadMembersUpdate(
                decodeSerializableElement(descriptor, index, ThreadMembersUpdate.Data.serializer()),
                sequence
            )
            EventName.ThreadUpdate -> ThreadUpdate(
                decodeSerializableElement(descriptor, index, ChannelData.serializer()),
                sequence
            )
            EventName.TypingStart -> TypingStart(
                decodeSerializableElement(descriptor, index, TypingStart.Data.serializer()),
                sequence
            )
            is EventName.Unknown -> {
                throw SerializationException("unknown Dispatch event cannot be deserialized")
            }
            EventName.UserUpdate -> UserUpdate(
                decodeSerializableElement(descriptor, index, UserData.serializer()),
                sequence
            )
            EventName.VoiceServerUpdate -> VoiceServerUpdate(
                decodeSerializableElement(descriptor, index, VoiceServerUpdate.Data.serializer()),
                sequence
            )
            EventName.VoiceStateUpdate -> VoiceStateUpdate(
                decodeSerializableElement(descriptor, index, VoiceStateData.serializer()),
                sequence
            )
            EventName.WebhooksUpdate -> WebhooksUpdate(
                decodeSerializableElement(descriptor, index, WebhooksUpdate.Data.serializer()),
                sequence
            )
        }
    }

}
