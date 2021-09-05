package dev.kord.discord.objects.api

import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

private const val SAFE_LENGTH = 19
private const val WIDTH = Byte.SIZE_BITS

fun EmptyBitSet() = DiscordBitSet(0)

@Serializable(with = DiscordBitSetSerializer::class)
class DiscordBitSet(internal var data: BigInteger) {

    val isEmpty: Boolean
        get() = data.isZero()

    val value: String
        get() = data.toString()

    val size: Int
        get() = data.numberOfWords * WIDTH

    val binary: String
        get() = data.toString(2)

    override fun equals(other: Any?): Boolean {
        if (other !is DiscordBitSet) return false
        return data == other.data
    }

    operator fun get(index: Int): Boolean {
        if (index !in 0 until size) return false
        return data.bitAt(index.toLong())
    }

    operator fun contains(other: DiscordBitSet): Boolean {
        if (other.size > size) return false

        for (i in 0..size.toLong()) {
            if (data.bitAt(i) and other.data.bitAt(i) != other.data.bitAt(i)) return false
        }

        return true
    }

    operator fun set(index: Int, value: Boolean) {
        data = data.setBitAt(index.toLong(), value)
    }

    operator fun plus(another: DiscordBitSet): DiscordBitSet {
        return DiscordBitSet(data.and(another.data))
    }

    operator fun minus(another: DiscordBitSet): DiscordBitSet {
        return DiscordBitSet(data.minus(another.data))
    }

    fun add(another: DiscordBitSet) {
        data = data.or(another.data)
    }


    fun remove(another: DiscordBitSet) {
        data = data.xor(another.data).and(data)
    }

    override fun hashCode(): Int = data.hashCode()

    override fun toString(): String {
        return "DiscordBitSet($binary)"
    }

}

fun DiscordBitSet(value: Long): DiscordBitSet {
    return DiscordBitSet(BigInteger(value))
}

fun DiscordBitSet(value: String): DiscordBitSet {
    return DiscordBitSet(BigInteger.parseString(value))
}

internal object DiscordBitSetSerializer : KSerializer<DiscordBitSet> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("DiscordBitSet", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): DiscordBitSet {
        return DiscordBitSet(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: DiscordBitSet) {
        encoder.encodeString(value.value)
    }

}
