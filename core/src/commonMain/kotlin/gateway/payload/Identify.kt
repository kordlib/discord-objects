package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordShard
import dev.kord.discord.objects.gateway.DiscordPresence
import dev.kord.discord.objects.gateway.Intents
import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.serializer.CommandSerializer
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalInt
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = Identify.Serializer::class)
data class Identify(
    override val data: Data,
) : Command<Identify.Data> {

    override val opcode: Opcode
        get() = Opcode.Identify

    @Serializable
    data class Data(
        internal val token: String,
        val properties: Properties,
        val compress: OptionalBoolean = OptionalBoolean.Missing,
        @SerialName("large_threshold")
        val largeThreshold: OptionalInt = OptionalInt.Missing,
        val shard: Optional<DiscordShard> = Optional.Missing(),
        val presence: Optional<DiscordPresence> = Optional.Missing(),
        val intents: Intents,
    ) {

        //hide the the bot token to prevent some unfortunate mistakes
        override fun toString(): String {
            return "Data(" +
                    "token='hunter2', " +
                    "properties=$properties, " +
                    "compress=$compress, " +
                    "largeThreshold=$largeThreshold, " +
                    "shard=$shard, " +
                    "presence=$presence, " +
                    "intents=$intents" +
                    ")"
        }

    }

    @Serializable
    data class Properties(
        @SerialName("\$os")
        val os: String,
        @SerialName("\$browser")
        val browser: String,
        @SerialName("\$device")
        val device: String,
    )

    internal object Serializer : KSerializer<Identify> by CommandSerializer(::Identify)

}
