package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.BotActivityData
import dev.kord.discord.objects.PresenceStatus
import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.serializer.CommandSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = UpdatePresence.Serializer::class)
data class UpdatePresence(
    override val data: Data
) : Command<UpdatePresence.Data> {

    override val opcode: Opcode
        get() = Opcode.StatusUpdate

    @Serializable
    data class Data(
        val since: Long?,
        val activities: List<BotActivityData>,
        val status: PresenceStatus,
        val afk: Boolean,
    )
    
    internal object Serializer : KSerializer<UpdatePresence> by CommandSerializer(::UpdatePresence)

}
