package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.DiscordShard
import dev.kord.discord.objects.gateway.DiscordPresence
import dev.kord.discord.objects.gateway.Intents
import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalInt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Identify(
    internal val token: String,
    val properties: Properties,
    val compress: OptionalBoolean = OptionalBoolean.Missing,
    @SerialName("large_threshold")
    val largeThreshold: OptionalInt = OptionalInt.Missing,
    val shard: Optional<DiscordShard> = Optional.Missing(),
    val presence: Optional<DiscordPresence> = Optional.Missing(),
    val intents: Intents,
) : Command {

    override val opcode: Opcode
        get() = Opcode.Identify

    //hide the the bot token to prevent some unfortunate mistakes
    override fun toString(): String = "Identify(" +
            "token=hunter2," +
            "properties=$properties," +
            "compress=$compress," +
            "largeThreshold=$largeThreshold," +
            "shard=$shard," +
            "presence=$presence" +
            ")"

    @Serializable
    class Properties(
        @SerialName("\$os")
        val os: String,
        @SerialName("\$browser")
        val browser: String,
        @SerialName("\$device")
        val device: String,
    )

}
