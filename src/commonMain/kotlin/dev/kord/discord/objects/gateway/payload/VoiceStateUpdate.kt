package dev.kord.discord.objects.dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.Command
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoiceStateUpdate(
    @SerialName("guild_id")
    val guildId: Snowflake,
    @SerialName("channel_id")
    val channelId: Snowflake?,
    @SerialName("self_mute")
    val selfMute: Boolean,
    @SerialName("self_deaf")
    val selfDeaf: Boolean,
) : Command {

    override val opcode: Opcode
        get() = Opcode.VoiceStateUpdate

}
