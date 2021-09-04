package dev.kord.discord.objects.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.TargetUserType
import dev.kord.discord.objects.UserFlags
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class InviteCreate(
    override val data: Data,
    override val sequence: Int
): DispatchEvent<InviteCreate.Data>() {
    override val name: EventName get() = EventName.InviteCreate

    @Serializable
    data class Data(
        @SerialName("channel_id")
        val channelId: Snowflake,
        val code: String,
        @SerialName("created_at")
        val createdAt: String,
        @SerialName("guild_id")
        val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
        val inviter: Optional<DiscordInviteUser> = Optional.Missing(),
        @SerialName("max_age")
        val maxAge: Int,
        @SerialName("max_uses")
        val maxUses: Int,
        @SerialName("target_user")
        val targetUser: Optional<DiscordInviteUser> = Optional.Missing(),
        @SerialName("target_user_type")
        val targetUserType: Optional<TargetUserType> = Optional.Missing(),
        val temporary: Boolean,
        val uses: Int,
    )

}

@Serializable
data class DiscordInviteUser(
    val id: Snowflake,
    val username: String,
    val discriminator: String,
    val avatar: String?,
    val bot: OptionalBoolean = OptionalBoolean.Missing,
    @SerialName("public_flags")
    val publicFlags: Optional<UserFlags> = Optional.Missing(),
)
