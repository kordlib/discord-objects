package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = InviteDelete.Serializer::class)
data class InviteDelete(
    override val data: Data,
    override val sequence: Int
): Dispatch<InviteDelete.Data>() {
    override val name: EventName get() = EventName.InviteDelete

    @Serializable
    data class Data(
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("guild_id")
        val guildId: Snowflake,
        val code: String,
    )

    internal object Serializer : KSerializer<InviteDelete> by DispatchSerializer(::InviteDelete)
}
