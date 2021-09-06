package dev.kord.discord.objects.gateway.payload

class Resumed(
    override val sequence: Int
) : DispatchEvent<Unit>() {

    override val name: EventName
        get() = EventName.Resumed

    override val data: Unit get() = Unit

}
