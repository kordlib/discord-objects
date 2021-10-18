package dev.kord.discord.objects

sealed class ApplicationCommandOptionTypeData<out T: Any> {
    abstract val value: T

    class String(override val value: kotlin.String): ApplicationCommandOptionTypeData<kotlin.String>()
    class Integer(override val value: Long): ApplicationCommandOptionTypeData<Long>()
    class Boolean(override val value: kotlin.Boolean): ApplicationCommandOptionTypeData<kotlin.Boolean>()
    class User(override val value: Snowflake): ApplicationCommandOptionTypeData<Snowflake>()
    class Channel(override val value: Snowflake): ApplicationCommandOptionTypeData<Snowflake>()
    class Role(override val value: Snowflake): ApplicationCommandOptionTypeData<Snowflake>()
    class Mentionable(override val value: Snowflake): ApplicationCommandOptionTypeData<Snowflake>()
    class Number(override val value: Double): ApplicationCommandOptionTypeData<Double>()
    abstract class Unknown<out T: Any> : ApplicationCommandOptionTypeData<T>()

}
