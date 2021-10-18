package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.gateway.payload.serializer.EventSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = EventSerializer::class)
sealed interface Event : Payload {

    companion object {
        fun serializer() : KSerializer<Event> = EventSerializer
    }

}
