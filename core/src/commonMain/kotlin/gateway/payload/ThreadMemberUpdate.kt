package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.ThreadMemberData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ThreadMemberUpdate.Serializer::class)
data class ThreadMemberUpdate(
    override val data: ThreadMemberData,
    override val sequence: Int
) : Dispatch<ThreadMemberData>() {
    override val name: EventName get() = EventName.ThreadMemberUpdate

    internal object Serializer : KSerializer<ThreadMemberUpdate> by DispatchSerializer(::ThreadMemberUpdate)
}
