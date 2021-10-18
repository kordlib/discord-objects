package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalInt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InviteData(
    val code: String,
    val guild: Optional<PartialGuildData> = Optional.Missing(),
    val channel: ChannelData,
    val inviter: Optional<UserData> = Optional.Missing(),
    @SerialName("target_user")
    val targetUser: Optional<UserData> = Optional.Missing(),
    @SerialName("target_user_type")
    val targetUserType: Optional<TargetUserType> = Optional.Missing(),
    @SerialName("approximate_presence_count")
    val approximatePresenceCount: OptionalInt = OptionalInt.Missing,
    @SerialName("approximate_member_count")
    val approximateMemberCount: OptionalInt = OptionalInt.Missing,
)
