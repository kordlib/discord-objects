package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class InviteDelete(
    override val data: Data,
    override val sequence: Int
): DispatchEvent<InviteDelete.Data>() {
    override val name: EventName get() = EventName.InviteDelete

    @Serializable
    data class Data(
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("guild_id")
        val guildId: Snowflake,
        val code: String,
    )
}
