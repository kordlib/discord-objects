package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.gateway.Opcode

sealed class DispatchEvent<out T> : Event {

    final override val opcode: Opcode
        get() = Opcode.Dispatch

    abstract val sequence: Int

    abstract val name: EventName

    abstract val data: T

}
