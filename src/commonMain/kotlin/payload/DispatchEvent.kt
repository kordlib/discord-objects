package dev.kord.discord.objects.payload

import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.api.hash

sealed class DispatchEvent<out T> : Event {

    final override val opcode: Opcode
        get() = Opcode.Dispatch

    abstract val sequence: Int

    abstract val name: EventName

    abstract val data: T

    final override fun toString(): String =
        "${name::class.simpleName}(data=$data, sequence=$sequence)"

    final override fun equals(other: Any?): Boolean {
        if (other !is DispatchEvent<*>) return false
        return name == other.name && data == other.data
    }

    final override fun hashCode(): Int = hash(data, name, sequence)

}
