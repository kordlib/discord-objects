package dev.kord.discord.objects

import dev.kord.discord.objects.api.EnumType
import dev.kord.discord.objects.api.EnumTypeCompanion
import dev.kord.discord.objects.api.serializer.IntEnumTypeSerializer
import kotlinx.serialization.Serializable

/**
 * An instance of [Discord Premium Types](https://discord.com/developers/docs/resources/user#user-object-premium-types).
 *
 * Premium types denote the level of premium a user has.
 */
@Serializable(with = UserPremium.Serialization::class)
sealed class UserPremium(override val value: Int) : EnumType<Int> {

    override fun equals(other: Any?): Boolean {
        if (other !is UserPremium) return false
        return other.value == value
    }

    override fun hashCode(): Int = value.hashCode()

    override fun toString(): String = this::class.simpleName!!

    class Unknown(value: Int) : UserPremium(value) {
        override fun toString(): String = "Unknown(value=$value)"
    }

    object None : UserPremium(0)
    object NitroClassic : UserPremium(1)
    object Nitro : UserPremium(2)

    companion object : EnumTypeCompanion<UserPremium> {

        override val values: Set<UserPremium>
            get() = setOf(None, Nitro, NitroClassic)

    }

    internal object Serialization : IntEnumTypeSerializer<UserPremium>(
        "UserPremium", values, ::Unknown
    )

}
