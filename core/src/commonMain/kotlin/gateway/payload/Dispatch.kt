package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(with = DispatchSerializer::class)
sealed class Dispatch<out T> : Event {

    final override val opcode: Opcode
        get() = Opcode.Dispatch

    abstract val sequence: Int

    abstract val name: EventName

    abstract val data: T

    companion object {

        @Suppress("UNCHECKED_CAST")
        public fun genericSerializer() : KSerializer<Dispatch<*>> = DispatchSerializer(Unit.serializer() as KSerializer<Any?>)

    }

}
