package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordBotActivity
import dev.kord.discord.objects.PresenceStatus
import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.serializer.CommandSerializer
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StatusUpdate.Serializer::class)
data class StatusUpdate(
    override val data: Data
) : Command<StatusUpdate.Data> {

    override val opcode: Opcode
        get() = Opcode.StatusUpdate

    @Serializable
    data class Data(
        val since: Long?,
        val activities: List<DiscordBotActivity>,
        val status: PresenceStatus,
        val afk: Boolean,
    )
    
    internal object Serializer : KSerializer<StatusUpdate> by CommandSerializer(::StatusUpdate)

}
