package dev.kord.discord.objects.payload

interface UnknownEvent : Event {
    val name: EventName?

    val sequence: Int?

    val data: Any?

}
