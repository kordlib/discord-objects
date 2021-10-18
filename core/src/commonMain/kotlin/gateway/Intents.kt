package dev.kord.discord.objects.gateway

import dev.kord.discord.objects.api.DiscordBitSet
import dev.kord.discord.objects.api.EmptyBitSet
import dev.kord.discord.objects.gateway.payload.Identify
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.js.JsName
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName

inline fun Intents(builder: Intents.IntentsBuilder.() -> Unit = {}): Intents {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    return Intents.IntentsBuilder().apply(builder).flags()
}

fun Intents(vararg intents: Intent) = Intents {
    intents.forEach { +it }
}

fun Intents(intents: Iterable<Intents>) = Intents {
    intents.forEach { +it }
}

fun Intents(value: String) = Intents(DiscordBitSet(value))

@JsName("IntentsWithIterable")
@JvmName("IntentsWithIterable")
fun Intents(intents: Iterable<Intent>) = Intents {
    intents.forEach { +it }
}

/**
 * A set of [intents][Intent] to be used while [identifying][Identify] a Gateway connection to communicate the events
 * the client wishes to receive.
 */
@JvmInline
@Serializable(with = Intents.Serializer::class)
value class Intents internal constructor(val code: DiscordBitSet) {
    /**
     *  Returns this [Intents] as a [Set] of [Intent] values.
     */
    val values get() = Intent.values.filter { it.code in code }.toSet()

    operator fun contains(intent: Intent) = intent.code in code

    /**
     * Returns an [Intents] that added the [intent] to this [code].
     */
    operator fun plus(intent: Intent): Intents = Intents(code + intent.code)

    /**
     * Returns an [Intents] that removed the [intent] from this [code].
     */
    operator fun minus(intent: Intent): Intents = Intents(code - intent.code)


    operator fun contains(intent: Intents) = intent.code in code

    /**
     * Returns an [Intents] that added the [intent] to this [code].
     */
    operator fun plus(intent: Intents): Intents = Intents(code + intent.code)

    /**
     * Returns an [Intents] that removed the [intent] from this [code].
     */
    operator fun minus(intent: Intents): Intents = Intents(code - intent.code)

    /**
     * copy this [Intents] and apply the [block] to it.
     */
    @OptIn(ExperimentalContracts::class)
    inline fun copy(block: IntentsBuilder.() -> Unit): Intents {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }
        val builder = IntentsBuilder(code)
        builder.apply(block)
        return builder.flags()
    }

    override fun toString(): String = "Intents(values=$values)"

    companion object {

        @PrivilegedIntent
        val all: Intents
            get() = Intents(Intent.values)

        @OptIn(PrivilegedIntent::class)
        val nonPrivileged: Intents
            get() = Intents {
                +all
                -Intent.GuildPresences
                -Intent.GuildMembers
            }

        val none: Intents = Intents()

    }

    class IntentsBuilder(internal var code: DiscordBitSet = EmptyBitSet()) {
        operator fun Intents.unaryPlus() {
            this@IntentsBuilder.code.add(code)
        }

        operator fun Intent.unaryPlus() {
            this@IntentsBuilder.code.add(code)
        }

        operator fun Intent.unaryMinus() {
            this@IntentsBuilder.code.remove(code)

        }

        fun flags() = Intents(code)
    }

    internal object Serializer : KSerializer<Intents> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("intents", PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): Intents {
            val intents = decoder.decodeString()
            return Intents(intents)
        }

        override fun serialize(encoder: Encoder, value: Intents) {
            val intents = value.code
            encoder.encodeString(intents.value)
        }
    }

}
