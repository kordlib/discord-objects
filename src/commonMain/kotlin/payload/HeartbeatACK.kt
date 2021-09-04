package dev.kord.discord.objects.payload

import dev.kord.discord.objects.gateway.Opcode

object HeartbeatACK : Event {
    override val opcode: Opcode
        get() = Opcode.HeartbeatACK

    override fun equals(other: Any?): Boolean {
        return other is HeartbeatACK
    }

    override fun toString(): String = "HeartbeatAck"

}
