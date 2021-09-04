package dev.kord.discord.objects.payload

import dev.kord.discord.objects.gateway.Opcode

class InvalidSession(
    val data: Boolean?
) : Event {

    override val opcode: Opcode
        get() = Opcode.InvalidSession

    override fun equals(other: Any?): Boolean {
        if (other !is InvalidSession) return false

        return data == other.data
    }

    override fun hashCode(): Int = data.hashCode()

    override fun toString(): String = "InvalidSession(data=$data)"

}
