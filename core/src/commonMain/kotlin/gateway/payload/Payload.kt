package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.serializer.PayloadSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = PayloadSerializer::class)
sealed interface Payload {
    val opcode: Opcode

    override fun toString(): String

    override fun equals(other: Any?): Boolean

    companion object {
        fun serializer() : KSerializer<Payload> = PayloadSerializer
    }
}
