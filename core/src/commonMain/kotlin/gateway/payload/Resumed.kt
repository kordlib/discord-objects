package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.gateway.payload.serializer.GenericDispatchSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer

@Serializable(with = Resumed.Serializer::class)
data class Resumed(
    override val sequence: Int
) : Dispatch<Unit?>() {

    override val name: EventName
        get() = EventName.Resumed

    override val data: Unit? get() = null

    internal object Serializer : GenericDispatchSerializer<Unit?, Resumed>(
        Resumed::class,
        Unit.serializer().nullable,
        { _, seq -> Resumed(seq) }
    )
}
