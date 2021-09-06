package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.gateway.Opcode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Resume(
    val token: String,
    @SerialName("session_id")
    val sessionId: String,
    @SerialName("seq")
    val sequenceNumber: Int,
) : Command {

    override val opcode: Opcode
        get() = Opcode.Resume

    //hide the the bot token to prevent some unfortunate mistakes
    override fun toString(): String = "Resume(token=hunter2,sessionId=$sessionId,sequenceNumber:$sequenceNumber)"
}
