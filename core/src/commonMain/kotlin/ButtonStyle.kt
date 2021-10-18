package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Representation of different ButtonStyles.
 *
 * A cheat sheet on how the styles look like can be found [here](https://discord.com/assets/7bb017ce52cfd6575e21c058feb3883b.png)
 *
 * @see ComponentType.Button
 */

@Serializable(with = ButtonStyle.Serializer::class)
sealed class ButtonStyle(val value: Int) {

    /**
     * A fallback style used for styles that haven't been added to Kord yet.
     */
    class Unknown(value: Int) : ButtonStyle(value)

    /**
     * Blurple.
     * Requires: [ComponentData.customId]
     */
    object Primary : ButtonStyle(1)

    /**
     * Grey.
     * Requires: [ComponentData.customId]
     */
    object Secondary : ButtonStyle(2)

    /**
     * Green
     * Requires: [ComponentData.customId]
     */
    object Success : ButtonStyle(3)

    /**
     * Red.
     * Requires: [ComponentData.customId]
     */
    object Danger : ButtonStyle(4)

    /**
     * Grey, navigates to an URL.
     * Requires: [ComponentData.url]
     */
    object Link : ButtonStyle(5)

    companion object Serializer : KSerializer<ButtonStyle> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("ButtonStyle", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): ButtonStyle =
            when (val value = decoder.decodeInt()) {
                1 -> Primary
                2 -> Secondary
                3 -> Success
                4 -> Danger
                5 -> Link
                else -> Unknown(value)
            }

        override fun serialize(encoder: Encoder, value: ButtonStyle) = encoder.encodeInt(value.value)
    }
}
