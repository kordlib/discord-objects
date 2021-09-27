package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.serializer.CommandSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = Resume.Serializer::class)
data class Resume(
    override val data: Data
) : Command<Resume.Data> {

    override val opcode: Opcode
        get() = Opcode.Resume

    @Serializable
    data class Data(
        val token: String,
        @SerialName("session_id")
        val sessionId: String,
        @SerialName("seq")
        val sequenceNumber: Int,
    ) {

        //hide the the bot token to prevent some unfortunate mistakes
        override fun toString(): String {
            return "Data(token='hunter2', sessionId='$sessionId', sequenceNumber=$sequenceNumber)"
        }

    }

    internal object Serializer : KSerializer<Resume> by CommandSerializer(::Resume)

}
