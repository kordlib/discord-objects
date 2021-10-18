package dev.kord.discord.objects

import dev.kord.discord.objects.api.DiscordBitSet
import dev.kord.discord.objects.api.EmptyBitSet
import kotlin.js.JsName
import kotlin.jvm.JvmName

fun Permissions(value: String) = Permissions(DiscordBitSet(value))

inline fun Permissions(block: Permissions.PermissionsBuilder.() -> Unit = {}): Permissions {
    val builder = Permissions.PermissionsBuilder(DiscordBitSet(0))
    builder.apply(block)
    return builder.permissions()
}

fun Permissions(vararg permissions: Permission) = Permissions {
    permissions.forEach { +it }
}

fun Permissions(vararg permissions: Permissions) = Permissions {
    permissions.forEach { +it }
}

fun Permissions(permissions: Iterable<Permission>) = Permissions {
    permissions.forEach { +it }
}


@JsName("PermissionWithIterable")
@JvmName("PermissionWithIterable")
fun Permissions(permissions: Iterable<Permissions>) = Permissions {
    permissions.forEach { +it }
}


sealed class Permission(val code: DiscordBitSet) {

    override fun toString(): String = this::class.simpleName!!

    constructor(value: Long) : this(DiscordBitSet(value))

    object CreateInstantInvite : Permission(0x00000001)
    object KickMembers : Permission(0x00000002)
    object BanMembers : Permission(0x00000004)
    object Administrator : Permission(0x00000008)
    object ManageChannels : Permission(0x00000010)
    object ManageGuild : Permission(0x00000020)
    object AddReactions : Permission(0x00000040)
    object ViewAuditLog : Permission(0x00000080)
    object Stream : Permission(0x00000200)
    object ViewChannel : Permission(0x00000400)
    object SendMessages : Permission(0x00000800)
    object SendTTSMessages : Permission(0x00001000)
    object ManageMessages : Permission(0x00002000)
    object EmbedLinks : Permission(0x00004000)
    object AttachFiles : Permission(0x00008000)
    object ReadMessageHistory : Permission(0x00010000)
    object MentionEveryone : Permission(0x00020000)
    object UseExternalEmojis : Permission(0x00040000)
    object ViewGuildInsights : Permission(0x00080000)
    object Connect : Permission(0x00100000)
    object Speak : Permission(0x00200000)
    object MuteMembers : Permission(0x00400000)
    object DeafenMembers : Permission(0x00800000)
    object MoveMembers : Permission(0x01000000)
    object UseVAD : Permission(0x02000000)
    object PrioritySpeaker : Permission(0x00000100)
    object ChangeNickname : Permission(0x04000000)
    object ManageNicknames : Permission(0x08000000)
    object ManageRoles : Permission(0x10000000)
    object ManageWebhooks : Permission(0x20000000)
    object ManageEmojis : Permission(0x40000000)
    object UseSlashCommands : Permission(0x80000000)
    object RequestToSpeak : Permission(0x100000000)
    object ManageThreads : Permission(0x0400000000)
    object CreatePublicThreads : Permission(0x0800000000)
    object CreatePrivateThreads : Permission(0x1000000000)
    object SendMessagesInThreads : Permission(0x4000000000)
    @Suppress("SELF_CALL_IN_NESTED_OBJECT_CONSTRUCTOR_WARNING")
    object All : Permission(values.fold(EmptyBitSet()) { acc, value -> acc.add(value.code); acc })

    companion object {
        val values: Set<Permission>
            get() = setOf(
                CreateInstantInvite,
                KickMembers,
                BanMembers,
                Administrator,
                ManageChannels,
                ManageGuild,
                AddReactions,
                ViewAuditLog,
                ViewChannel,
                SendMessages,
                SendTTSMessages,
                ManageMessages,
                EmbedLinks,
                AttachFiles,
                ReadMessageHistory,
                MentionEveryone,
                UseExternalEmojis,
                ViewGuildInsights,
                Connect,
                Speak,
                MuteMembers,
                DeafenMembers,
                MoveMembers,
                UseVAD,
                PrioritySpeaker,
                ChangeNickname,
                ManageNicknames,
                ManageRoles,
                ManageWebhooks,
                ManageEmojis,
                UseSlashCommands,
                RequestToSpeak,
                ManageThreads,
                CreatePublicThreads,
                CreatePrivateThreads,
                SendMessagesInThreads
            )
    }
}
