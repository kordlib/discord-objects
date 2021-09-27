package dev.kord.discord.objects.gateway.payload

sealed interface Command<T> : Payload {

    val data : T

}
