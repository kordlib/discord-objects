package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.gateway.Opcode

class Heartbeat(
    val data: Int?
) : Event, Command {
    override val opcode: Opcode get() = Opcode.Heartbeat

    override fun equals(other: Any?): Boolean {
        if (other !is Heartbeat) return false

        return data == other.data
    }

    override fun hashCode(): Int = data.hashCode()

    override fun toString(): String = "Heartbeat(data=$data)"

}
