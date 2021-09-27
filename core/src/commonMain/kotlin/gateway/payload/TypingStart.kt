package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordTyping
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = TypingStart.Serializer::class)
data class TypingStart(
    override val data: DiscordTyping,
    override val sequence: Int
) : Dispatch<DiscordTyping>() {
    override val name: EventName get() = EventName.TypingStart
    
    internal object Serializer : KSerializer<TypingStart> by DispatchSerializer(::TypingStart)
}
