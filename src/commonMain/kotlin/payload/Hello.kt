package dev.kord.discord.objects.payload

import dev.kord.discord.objects.gateway.Opcode
import kotlinx.serialization.Serializable

class Hello(val data: Data) : Event {

    override val opcode: Opcode
        get() = Opcode.Hello

    override fun equals(other: Any?): Boolean {
        if (other !is Hello) return false

        return data == other.data
    }

    override fun toString(): String = "Hello(data=$data)"

    @Serializable
    class Data(val heartbeatInterval: Long)

}
