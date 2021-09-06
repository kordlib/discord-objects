package dev.kord.discord.objects.gateway.payload

interface UnknownEvent : Event {
    val name: EventName?

    val sequence: Int?

    val data: Any?

}
