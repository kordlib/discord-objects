package dev.kord.discord.objects.payload

import dev.kord.discord.objects.gateway.Opcode

sealed interface Event {
    val opcode: Opcode

    override fun toString(): String

    override fun equals(other: Any?): Boolean

}
