package dev.kord.discord.objects.gateway

import dev.kord.discord.objects.api.EnumType
import dev.kord.discord.objects.api.EnumTypeCompanion
import dev.kord.discord.objects.api.serializer.IntEnumTypeSerializer
import kotlinx.serialization.Serializable

@Serializable(with = Opcode.Serializer::class)
sealed class Opcode(override val value: Int) : EnumType<Int> {

    override fun equals(other: Any?): Boolean {
        return if (other !is Opcode) false
        else other.value == value
    }

    override fun hashCode(): Int = value.hashCode()

    override fun toString(): String = this::class.simpleName.orEmpty()

    /** The default code for unknown values. */
    class Unknown(value: Int) : Opcode(value) {
        override fun toString(): String = "Unknown(value=$value)"
    }

    /**
     * An event was dispatched.
     */
    object Dispatch : Opcode(0)

    /**
     * Fired periodically by the client to keep the connection alive.
     */
    object Heartbeat : Opcode(1)

    /**
     * Starts a new session during the initial handshake.
     */
    object Identify : Opcode(2)

    /**
     * Update the client's presence.
     */
    object StatusUpdate : Opcode(3)

    /**
     * Used to join/leave or move between voice channels.
     */
    object VoiceStateUpdate : Opcode(4)

    /**
     * You should attempt to reconnect and resume immediately.
     */
    object Resume : Opcode(6)

    /**
     * You should attempt to reconnect and resume immediately.
     */
    object Reconnect : Opcode(7)

    /**
     * Request information about offline guild members in a large guild.
     */
    object RequestGuildMembers : Opcode(8)

    /**
     * The session has been invalidated. You should reconnect and identify/resume accordingly.
     */
    object InvalidSession : Opcode(9)

    /**
     * Sent immediately after connecting, contains the `heartbeat_interval` to use.
     */
    object Hello : Opcode(10)

    /**
     * Sent in response to receiving a heartbeat to acknowledge that it has been received.
     */
    object HeartbeatACK : Opcode(11)

    companion object : EnumTypeCompanion<Opcode> {

        operator fun invoke(value: Int): Opcode = values.firstOrNull { it.value == value } ?: Unknown(value)

        override val values: Set<Opcode>
            get() = setOf(
                Dispatch,
                Heartbeat,
                Identify,
                StatusUpdate,
                VoiceStateUpdate,
                Resume,
                Reconnect,
                RequestGuildMembers,
                InvalidSession,
                Hello
            )

    }

    internal object Serializer : IntEnumTypeSerializer<Opcode>(
        "Opcode", values, ::Unknown
    )

}
