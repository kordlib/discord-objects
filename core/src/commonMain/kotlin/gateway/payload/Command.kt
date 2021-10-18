package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.gateway.payload.serializer.CommandSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(with = CommandSerializer::class)
sealed interface Command<T> : Payload {

    val data : T

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun genericSerializer() : KSerializer<Command<*>> = CommandSerializer(Unit.serializer()) as KSerializer<Command<*>>
    }
}
