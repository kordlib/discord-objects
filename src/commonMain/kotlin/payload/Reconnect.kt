package dev.kord.discord.objects.payload

import dev.kord.discord.objects.gateway.Opcode

object Reconnect : Event {
    override val opcode: Opcode
        get() = Opcode.Reconnect

    override fun equals(other: Any?): Boolean {
        return other is Reconnect
    }

    override fun toString(): String = "Reconnect"

}
