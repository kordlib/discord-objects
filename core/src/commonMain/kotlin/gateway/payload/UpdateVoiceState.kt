package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.serializer.CommandSerializer
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = UpdateVoiceState.Serializer::class)
data class UpdateVoiceState(
    override val data: Data
) : Command<UpdateVoiceState.Data> {

    override val opcode: Opcode
        get() = Opcode.VoiceStateUpdate

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: Snowflake,
        @SerialName("channel_id")
        val channelId: Snowflake?,
        @SerialName("self_mute")
        val selfMute: Boolean,
        @SerialName("self_deaf")
        val selfDeaf: Boolean,
    )

    internal object Serializer : KSerializer<UpdateVoiceState> by CommandSerializer(::UpdateVoiceState)

}
