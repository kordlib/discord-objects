package dev.kord.discord.objects

import dev.kord.discord.objects.api.DiscordBitSet
import dev.kord.discord.objects.api.EmptyBitSet
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
import kotlin.jvm.JvmInline

inline fun ApplicationFlags(builder: ApplicationFlags.Builder.() -> Unit = {}): ApplicationFlags {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    return ApplicationFlags.Builder().apply(builder).flags()
}

fun ApplicationFlags(vararg flags: ApplicationFlag) = ApplicationFlags {
    flags.forEach { +it }
}

fun ApplicationFlags(intents: Iterable<ApplicationFlag>) = ApplicationFlags {
    intents.forEach { +it }
}

fun ApplicationFlags(value: String) = ApplicationFlags(DiscordBitSet(value))

@JvmInline
@Serializable(with = ApplicationFlags.Serializer::class)
value class ApplicationFlags internal constructor(val code: DiscordBitSet) {
    /**
     *  Returns this [ApplicationFlags] as a [Set] of [ApplicationFlag] values.
     */
    val values get() = ApplicationFlag.values.filter { it.code in code }.toSet()

    operator fun contains(flag: ApplicationFlag) = flag.code in code

    /**
     * Returns an [ApplicationFlags] that added the [flag] to this [code].
     */
    operator fun plus(flag: ApplicationFlag): ApplicationFlags = ApplicationFlags(code + flag.code)

    /**
     * Returns an [ApplicationFlags] that removed the [flag] from this [code].
     */
    operator fun minus(flag: ApplicationFlag): ApplicationFlags = ApplicationFlags(code - flag.code)


    operator fun contains(flag: ApplicationFlags) = flag.code in code

    /**
     * Returns an [ApplicationFlags] that added the [flag] to this [code].
     */
    operator fun plus(flag: ApplicationFlags): ApplicationFlags = ApplicationFlags(code + flag.code)

    /**
     * Returns an [ApplicationFlags] that removed the [flag] from this [code].
     */
    operator fun minus(flag: ApplicationFlags): ApplicationFlags = ApplicationFlags(code - flag.code)

    /**
     * copy this [ApplicationFlags] and apply the [block] to it.
     */
    @OptIn(ExperimentalContracts::class)
    inline fun copy(block: Builder.() -> Unit): ApplicationFlags {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }
        val builder = Builder(code)
        builder.apply(block)
        return builder.flags()
    }

    override fun toString(): String = "ApplicationFlags(values=$values)"

    companion object

    class Builder(internal var code: DiscordBitSet = EmptyBitSet()) {
        operator fun ApplicationFlags.unaryPlus() {
            this@Builder.code.add(code)
        }

        operator fun ApplicationFlag.unaryPlus() {
            this@Builder.code.add(code)
        }

        operator fun ApplicationFlag.unaryMinus() {
            this@Builder.code.remove(code)

        }

        fun flags() = ApplicationFlags(code)
    }

    internal object Serializer : KSerializer<ApplicationFlags> {
        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("dev.kord.discord.objects.ApplicationFlags", PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): ApplicationFlags {
            val intents = decoder.decodeString()
            return ApplicationFlags(intents)
        }

        override fun serialize(encoder: Encoder, value: ApplicationFlags) {
            val intents = value.code
            encoder.encodeString(intents.value)
        }
    }

}
