package dev.kord.discord.objects

import kotlinx.serialization.Serializable

sealed class ApplicationCommandOptionChoiceData<out T> {
    abstract val name: kotlin.String
    abstract val value: T

    @Serializable
    data class Int(
        override val name: kotlin.String,
        override val value: kotlin.Int,
    ) : ApplicationCommandOptionChoiceData<kotlin.Int>()

    @Serializable
    data class Double(
        override val name: kotlin.String,
        override val value: kotlin.Double,
    ) : ApplicationCommandOptionChoiceData<kotlin.Double>()

    @Serializable
    data class String(
        override val name: kotlin.String,
        override val value: kotlin.String,
    ) : ApplicationCommandOptionChoiceData<kotlin.String>()

}
