package payload.json

import dev.kord.discord.objects.payloadFile
import dev.kord.discord.objects.gateway.payload.*
import dev.kord.discord.objects.json
import kotlinx.serialization.KSerializer
import kotlin.test.Test
import kotlin.test.assertEquals

class PayloadSerializationTests {

    @Suppress("LocalVariableName")
    private inline fun<reified T: Payload>testEncode(serializer: KSerializer<T>){
        val fileContent = payloadFile(T::class.simpleName!!.lowercase())
        val decoded = json.decodeFromString(serializer, fileContent)
        val encoded = json.encodeToString(serializer, decoded)
        val `re-decoded` = json.decodeFromString(serializer, encoded)
        assertEquals(decoded, `re-decoded`)
    }

    @Test
    fun ack() = testEncode(HeartbeatAck.serializer())

    @Test
    fun channelCreate() = testEncode(ChannelCreate.serializer())

    @Test
    fun channelDelete() = testEncode(ChannelDelete.serializer())

    @Test
    fun channelPinsUpdate() = testEncode(ChannelPinsUpdate.serializer())

    @Test
    fun channelUpdate() = testEncode(ChannelUpdate.serializer())

    @Test
    fun guildBanAdd() = testEncode(GuildBanAdd.serializer())

    @Test
    fun guildBanRemove() = testEncode(GuildBanRemove.serializer())

    @Test
    fun guildCreate() = testEncode(GuildCreate.serializer())

    @Test
    fun guildDelete() = testEncode(GuildDelete.serializer())

    @Test
    fun guildEmojisUpdate() = testEncode(GuildEmojisUpdate.serializer())

    @Test
    fun guildIntegrationsUpdate() = testEncode(GuildIntegrationsUpdate.serializer())

    @Test
    fun guildMemberAdd() = testEncode(GuildMemberAdd.serializer())

    @Test
    fun guildMemberRemove() = testEncode(GuildMemberRemove.serializer())

    @Test
    fun guildMembersChunk() = testEncode(GuildMembersChunk.serializer())

    @Test
    fun guildMemberUpdate() = testEncode(GuildMemberUpdate.serializer())

    @Test
    fun guildRoleCreate() = testEncode(GuildRoleCreate.serializer())

    @Test
    fun guildRoleDelete() = testEncode(GuildRoleDelete.serializer())

    @Test
    fun guildRoleUpdate() = testEncode(GuildRoleUpdate.serializer())

    //TODO
//    @Test
//    fun guildStickersUpdate() = testEncode<GuildStickersUpdate>()

    @Test
    fun guildUpdate() = testEncode(GuildUpdate.serializer())

    @Test
    fun heartbeat() = testEncode(Heartbeat.serializer())

    @Test
    fun heartbeatAck() = testEncode(HeartbeatAck.serializer())

    @Test
    fun hello() = testEncode(Hello.serializer())

    @Test
    fun identify() = testEncode(Identify.serializer())

    @Test
    fun interactionCreate() = testEncode(InteractionCreate.serializer())

    @Test
    fun invalidSession() = testEncode(InvalidSession.serializer())

    @Test
    fun inviteCreate() = testEncode(InviteCreate.serializer())

    @Test
    fun inviteDelete() = testEncode(InviteDelete.serializer())

    @Test
    fun messageDeleteBulk() = testEncode(MessageDeleteBulk.serializer())

    @Test
    fun messageCreate() = testEncode(MessageCreate.serializer())

    @Test
    fun messageDelete() = testEncode(MessageDelete.serializer())

    @Test
    fun messageReactionAdd() = testEncode(MessageReactionAdd.serializer())

    @Test
    fun messageReactionRemove() = testEncode(MessageReactionRemove.serializer())

    @Test
    fun messageReactionRemoveAll() = testEncode(MessageReactionRemoveAll.serializer())

    @Test
    fun messageReactionRemoveEmoji() = testEncode(MessageReactionRemoveEmoji.serializer())

    @Test
    fun messageUpdate() = testEncode(MessageUpdate.serializer())

    //can't parse this since common is unable to parse presences
    //@Test
    //fun presenceUpdate() = testEncode<PresenceUpdate>()

    @Test
    fun ready() = testEncode(Ready.serializer())

    @Test
    fun reconnect() = testEncode(Reconnect.serializer())

    @Test
    fun requestGuildMembers() = testEncode(RequestGuildMembers.serializer())

    @Test
    fun resume() = testEncode(Resume.serializer())

    @Test
    fun resumed() = testEncode(Resumed.serializer())

    @Test
    fun statusUpdate() = testEncode(UpdatePresence.serializer())

    @Test
    fun threadCreate() = testEncode(ThreadCreate.serializer())

    @Test
    fun threadDelete() = testEncode(ThreadDelete.serializer())

    @Test
    fun threadListSync() = testEncode(ThreadListSync.serializer())

    @Test
    fun threadMembersUpdate() = testEncode(ThreadMembersUpdate.serializer())

    @Test
    fun threadMemberUpdate() = testEncode(ThreadMemberUpdate.serializer())

    @Test
    fun threadUpdate() = testEncode(ThreadUpdate.serializer())

    @Test
    fun typingStart() = testEncode(TypingStart.serializer())

    @Test
    fun updateVoiceState() = testEncode(UpdateVoiceState.serializer())

    @Test
    fun userUpdate() = testEncode(UserUpdate.serializer())

    @Test
    fun voiceServerUpdate() = testEncode(VoiceServerUpdate.serializer())

    @Test
    fun voiceStateUpdate() = testEncode(VoiceStateUpdate.serializer())

    @Test
    fun webhooksUpdate() = testEncode(WebhooksUpdate.serializer())

}
