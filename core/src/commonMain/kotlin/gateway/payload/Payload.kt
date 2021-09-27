package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.gateway.Opcode

sealed interface Payload {
    val opcode: Opcode

    override fun toString(): String

    override fun equals(other: Any?): Boolean
}
