package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.ChannelData
import dev.kord.discord.objects.ShardData
import dev.kord.discord.objects.UnavailableGuildData
import dev.kord.discord.objects.UserData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = Ready.Serializer::class)
data class Ready(
    override val data: Data,
    override val sequence: Int
) : Dispatch<Ready.Data>() {
    override val name: EventName get() = EventName.Ready

    @Serializable
    data class Data(
        @SerialName("v")
        val version: Int,
        val user: UserData,
        @SerialName("private_channels")
        val privateChannels: List<ChannelData>,
        val guilds: List<UnavailableGuildData>,
        @SerialName("session_id")
        val sessionId: String,
        val shard: Optional<ShardData> = Optional.Missing(),
    )
    
    internal object Serializer : KSerializer<Ready> by DispatchSerializer(::Ready)

}
