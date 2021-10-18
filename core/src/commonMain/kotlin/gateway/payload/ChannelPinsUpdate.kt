package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ChannelPinsUpdate.Serializer::class)
data class ChannelPinsUpdate(
    override val data: Data,
    override val sequence: Int
) : Dispatch<ChannelPinsUpdate.Data>() {
    override val name: EventName get() = EventName.ChannelPinsUpdate

    @Serializable
    data class Data(
        @SerialName("guild_id")
        val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("last_pin_timestamp")
        /*
        Do not trust the docs:
        2020-11-06 Docs mention this being optional only, but unpinning a channel results
        in this field being null.
        */
        val lastPinTimestamp: Optional<String?> = Optional.Missing()
    )


    internal object Serializer : KSerializer<ChannelPinsUpdate> by DispatchSerializer(::ChannelPinsUpdate)
}
