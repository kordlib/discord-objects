package dev.kord.discord.objects

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

fun UserFlags(flag: UserFlag) : UserFlags = UserFlags(flag.code)

fun UserFlags(vararg flags: UserFlag) : UserFlags = flags.fold(UserFlags(0)) {acc, userFlag ->
    acc.plus(userFlag)
}

@OptIn(ExperimentalContracts::class)
inline fun UserFlags(builder: UserFlags.Builder.() -> Unit): UserFlags {
    contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
    return UserFlags.Builder().apply(builder).build()
}

@JvmInline
@Serializable(with = UserFlags.Serializer::class)
value class UserFlags internal constructor(val code: Int) {

    val flags: Set<UserFlag> get() = UserFlag.values().filterTo(mutableSetOf()) { code and it.code != 0 }

    operator fun contains(flag: UserFlag) = flag in flags

    operator fun plus(flag: UserFlag): UserFlags = UserFlags(this.code or flag.code)

    operator fun plus(flags: UserFlags): UserFlags = UserFlags(this.code or flags.code)

    operator fun minus(flag: UserFlag): UserFlags =  UserFlags(code xor flag.code and code)

    operator fun minus(flags: UserFlags): UserFlags = UserFlags(code xor flags.code and code)

    @OptIn(ExperimentalContracts::class)
    inline fun copy(block: Builder.() -> Unit): UserFlags {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }
        val builder = Builder(this)
        builder.apply(block)
        return builder.build()
    }


    internal object Serializer : KSerializer<UserFlags> {

        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("userFlag", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): UserFlags {
            val flags = decoder.decodeInt()
            return UserFlags(flags)
        }

        override fun serialize(encoder: Encoder, value: UserFlags) {
            encoder.encodeInt(value.code)
        }

    }

    class Builder(internal var flags: UserFlags = UserFlags(UserFlag.None)) {

        operator fun UserFlag.unaryPlus() {
            this@Builder.flags += this
        }

        operator fun UserFlag.unaryMinus() {
            this@Builder.flags -= this
        }

        operator fun UserFlags.unaryPlus() {
            this@Builder.flags += this
        }

        operator fun UserFlags.unaryMinus() {
            this@Builder.flags -= this
        }

        fun add(flag: UserFlag) = +flag

        fun remove(flag: UserFlag) = -flag

        fun add(flags: UserFlags) = +flags

        fun remove(flags: UserFlags) = -flags

        fun build(): UserFlags = flags
    }

}
