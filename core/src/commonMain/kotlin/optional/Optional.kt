@file:Suppress("FunctionName")

package dev.kord.discord.objects.optional

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.optional.Optional.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlin.js.JsName
import kotlin.jvm.JvmName

/**
 * Represents a value that encapsulates all [three possible states of a value in the Discord API](https://discord.com/developers/docs/reference#nullable-and-optional-resource-fields).
 * Specifically:
 *
 * * [Missing] - a field that was not present in the serialized entity
 * * [Null] - a field that was assigned null in the serialized entity
 * * [Value] - a field that was assigned a non-null value in the serialized entity.
 *
 * The base class is  (de)serializable with kotlinx.serialization and should be used as follows:
 *
 * * `Optional<T>` - a field that is only optional but not nullable.
 * * `Optional<T?>` - A field that is both optional and nullable.
 * * A field that is only nullable should be represented as `T?` instead.
 *
 * Trying to deserialize `null` as `Optional<T>` will result in a [SerializationException] being thrown.
 *
 * Note that kotlinx.serialization does **not** call serializers for values that are not
 * present in the serialized format. `Optional` fields should have a default value of `Optional.Missing`:
 *
 * ```kotlin
 * @Serializable
 * class DiscordUser(
 *     val id: Long,
 *     val username: String,
 *     val bot: Optional<Boolean?> = Optional.Missing()
 * )
 * ```
 */
@Serializable(with = OptionalSerializer::class)
sealed interface Optional<out T> {

    /**
     * The value this optional wraps.
     * * Both [Missing] and [Null] will always return `null`.
     * * [Value] will always return a non-null value.
     */
    val value: T?

    /**
     * Represents a field that was not present in the serialized entity.
     */
    class Missing<out T> private constructor() : Optional<T> {

        /**
         * The value this optional wraps, always `null`.
         */
        override val value: T?
            get() = null

        override fun toString(): String = "Optional.Missing"

        override fun equals(other: Any?): Boolean {
            if (other !is Optional<*>) return false
            return other.value == value
        }

        override fun hashCode(): Int = value.hashCode()

        companion object {
            internal val constantMissing = Missing<Nothing>()
        }
    }

    /**
     * Represents a field that was assigned null in the serialized entity.
     */
    class Null<out T> private constructor() : Optional<T?> {

        /**
         * The value this optional wraps, always `null`.
         */
        override val value: T?
            get() = null

        override fun toString(): String = "Optional.Null"

        override fun equals(other: Any?): Boolean {
            return other is Null<*>
        }

        override fun hashCode(): Int = 0

        companion object {
            internal val constantNull = Null<Nothing>()

        }
    }

    /**
     * Represents a field that was assigned a non-null value in the serialized entity.
     * Equality and hashcode is implemented through its [value].
     *
     * @param value the value this optional wraps.
     */
    class Value<T : Any>(override val value: T) : Optional<T> {
        override fun toString(): String = "Optional.Something(content=$value)"

        /**
         * Destructures this optional to its [value].
         */
        operator fun component1(): T = value

        override fun equals(other: Any?): Boolean {
            val value = other as? Value<*> ?: return false
            return value.value == this.value
        }

        override fun hashCode(): Int = value.hashCode()
    }

    companion object {

        fun<T> serializer(type: KSerializer<T>): KSerializer<Optional<T>> = OptionalSerializer(type)

        fun<T: Any> Missing(): Missing<T> = Missing.constantMissing

        fun<T: Any> Null(): Null<T> = Null.constantNull


        fun <T, C : Collection<T>> missingOnEmpty(value: C): Optional<C> =
            if (value.isEmpty()) Missing()
            else Value(value)

    }


}

fun <T : Any> Optional(): Missing<T> = Optional.Missing()

@JsName("OptionalValue")
fun <T : Any> Optional(value: T): Value<T> = Value(value)

@JsName("OptionalNullable")
fun <T : Any> Optional(value: T?): Optional<T?> = when(value){
    null -> Optional.Null()
    else -> Value(value)
}

fun <T : Any> Optional<T>.switchOnMissing(value: T): Optional<T> = when (this) {
    is Missing -> Value(value)
    is Null<*>, is Value -> this
}

fun <T : Any> Optional<T>.switchOnMissing(value: Optional<T>): Optional<T> = when (this) {
    is Missing -> value
    is Null<*>, is Value -> this
}

fun <E> Optional<List<E>>.orEmpty(): List<E> = when (this) {
    is Missing, is Null<*> -> emptyList()
    is Value -> value
}

fun <E> Optional<Set<E>>.orEmpty(): Set<E> = when (this) {
    is Missing, is Null<*> -> emptySet()
    is Value -> value
}

@Suppress("UNCHECKED_CAST")
inline fun <E, T> Optional<List<E>>.mapList(mapper: (E) -> T): Optional<List<T>> = when (this) {
    is Missing, is Null<*> -> this as Optional<List<T>>
    is Value -> Value(value.map(mapper))
}


@Suppress("UNCHECKED_CAST")
inline fun <K, V, R> Optional<Map<K, V>>.mapValues(mapper: (Map.Entry<K, V>) -> R): Optional<Map<K, R>> = when (this) {
    is Missing, is Null<*> -> this as Optional<Map<K, R>>
    is Value -> Value(value.mapValues(mapper))
}


@Suppress("UNCHECKED_CAST")
inline fun <E> Optional<List<E>>.filterList(mapper: (E) -> Boolean): Optional<List<E>> = when (this) {
    is Missing, is Null<*> -> this
    is Value -> Value(value.filter(mapper))
}

@Suppress("UNCHECKED_CAST")
inline fun <reified R> Optional<List<*>>.filterInstanceOfList(): Optional<List<R>> = when (this) {
    is Missing, is Null<*> -> this as Optional<List<R>>
    is Value -> Value(value.filterIsInstance<R>())
}


@Suppress("UNCHECKED_CAST")
inline fun <E : Any, T : Any> Optional<E>.map(mapper: (E) -> T): Optional<T> = when (this) {
    is Missing, is Null<*> -> this as Optional<T>
    is Value -> Value(mapper(value))
}

/**
 * Applies the [mapper] to the optional if it is a [Value], returns the same optional otherwise.
 */
@Suppress("UNCHECKED_CAST")
inline fun <E : Any, T : Any> Optional<E>.flatMap(mapper: (E) -> Optional<T>): Optional<T> = when (this) {
    is Missing, is Null<*> -> this as Optional<T>
    is Value -> mapper(value)
}

@Suppress("UNCHECKED_CAST")
@JsName("mapNullableResult")
@JvmName("mapNullableResult")
inline fun <E : Any, T : Any> Optional<E?>.map(mapper: (E) -> T): Optional<T?> = when (this) {
    is Missing, is Null<*> -> this as Optional<T>
    is Value -> Value(mapper(value!!))
}

@Suppress("UNCHECKED_CAST")
inline fun <E, T> Optional<E>.mapNullable(mapper: (E) -> T): Optional<T?> = when (this) {
    is Missing, is Null<*> -> this as Optional<T>
    is Value -> Optional(mapper(value))
}

@Suppress("UNCHECKED_CAST")
inline fun <E : Any, T> Optional<E?>.mapNotNull(mapper: (E) -> T): Optional<T?> = when (this) {
    is Missing -> this as Optional<T?>
    is Null<*> -> this as Optional<T?>
    is Value -> Optional(mapper(value!!))
}

inline fun <E> Optional<List<E>>.firstOrNull(predicate: (E) -> Boolean): E? = when (this) {
    is Missing, is Null<*> -> null
    is Value -> value.firstOrNull(predicate)
}


inline fun <E> Optional<List<E>>.first(predicate: (E) -> Boolean): E = firstOrNull(predicate)!!


inline fun <E : Any> Optional<E>.mapToSnowflake(mapper: (E) -> Snowflake): OptionalSnowflake = when (this) {
    is Missing, is Null<*> -> OptionalSnowflake.Missing
    is Value -> OptionalSnowflake.Value(mapper(value))
}

@JsName("mapNullableSnowflake")
@JvmName("mapNullableSnowflake")
inline fun <E : Any> Optional<E?>.mapToSnowflake(mapper: (E) -> Snowflake): OptionalSnowflake? = when (this) {
    is Missing -> OptionalSnowflake.Missing
    is Null<*> -> null
    is Value -> OptionalSnowflake.Value(mapper(value!!))
}

inline fun <T, R : Any> Optional<T>.unwrap(mapper: (T) -> R): R? = when (this) {
    is Missing, is Null<*> -> null
    is Value -> mapper(value)
}

@Suppress("UNCHECKED_CAST")
fun <T : Any> Optional<T?>.coerceToMissing(): Optional<T> = when (this) {
    is Missing, is Null -> Optional.Missing()
    is Value -> this as Value<T>
}

@Suppress("RemoveRedundantQualifierName")
fun <T : Any> T.optional(): Optional.Value<T> = Optional.Value(this)

fun <T : Any?> T?.optional(): Optional<T?> = Optional(this)

fun Optional<Boolean>.toPrimitive(): OptionalBoolean = when (this) {
    is Value -> OptionalBoolean.Value(value)
    else -> OptionalBoolean.Missing
}

fun Optional<Int>.toPrimitive(): OptionalInt = when (this) {
    is Value -> OptionalInt.Value(value)
    else -> OptionalInt.Missing
}

fun Optional<Long>.toPrimitive(): OptionalLong = when (this) {
    is Value -> OptionalLong.Value(value)
    else -> OptionalLong.Missing
}
