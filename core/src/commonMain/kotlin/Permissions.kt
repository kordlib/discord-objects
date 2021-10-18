package dev.kord.discord.objects

import dev.kord.discord.objects.api.DiscordBitSet
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

@Serializable(with = Permissions.Companion::class)
class Permissions constructor(val code: DiscordBitSet) {
    /**
     *  Returns this [Permissions] as a [Set] of [Permission]
     */
    val values = Permission.values.filter { it.code in code }.toSet()

    operator fun plus(permission: Permission): Permissions = Permissions(code + permission.code)


    operator fun minus(permission: Permission): Permissions = Permissions(code - permission.code)


    operator fun contains(permission: Permission): Boolean {
        return permission.code in code
    }

    operator fun plus(permission: Permissions): Permissions = Permissions(code + permission.code)


    operator fun minus(permission: Permissions): Permissions = Permissions(code - permission.code)


    operator fun contains(permission: Permissions): Boolean {
        return permission.code in code
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Permissions) return false
        return other.code == code
    }

    override fun hashCode(): Int = code.hashCode()

    @OptIn(ExperimentalContracts::class)
    inline fun copy(block: PermissionsBuilder.() -> Unit): Permissions {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }
        val builder = PermissionsBuilder(code)
        builder.apply(block)
        return builder.permissions()
    }

    override fun toString(): String {
        return "Permissions(values=$values)"
    }

    companion object : KSerializer<Permissions> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("permission", PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): Permissions {
            val permissions = decoder.decodeString()
            return Permissions(permissions)
        }

        override fun serialize(encoder: Encoder, value: Permissions) {
            val permissionsSet = value.code.value
            encoder.encodeString(permissionsSet)
        }

    }

    class PermissionsBuilder(internal val code: DiscordBitSet) {
        operator fun Permissions.unaryPlus() {
            this@PermissionsBuilder.code.add(code)
        }

        operator fun Permissions.unaryMinus() {
            this@PermissionsBuilder.code.remove(code)
        }

        operator fun Permission.unaryPlus() {
            this@PermissionsBuilder.code.add(code)
        }

        operator fun Permission.unaryMinus() {
            this@PermissionsBuilder.code.remove(code)
        }

        fun permissions() = Permissions(code)
    }
}
