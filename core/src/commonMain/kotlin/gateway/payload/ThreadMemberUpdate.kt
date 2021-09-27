package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordThreadMember
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ThreadMemberUpdate.Serializer::class)
data class ThreadMemberUpdate(
    override val data: DiscordThreadMember,
    override val sequence: Int
) : Dispatch<DiscordThreadMember>() {
    override val name: EventName get() = EventName.ThreadMemberUpdate

    internal object Serializer : KSerializer<ThreadMemberUpdate> by DispatchSerializer(::ThreadMemberUpdate)
}
