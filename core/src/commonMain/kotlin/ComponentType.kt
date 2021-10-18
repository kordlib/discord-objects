package dev.kord.discord.objects

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Representation of different [ComponentData] types.
 *
 * @property value the raw type value used by the Discord API
 */

@Serializable(with = ComponentType.Serializer::class)
sealed class ComponentType(val value: Int) {

    /**
     * Fallback type used for types that haven't been added to Kord yet.
     */
    class Unknown(value: Int) : ComponentType(value)

    /**
     * A container for other components.
     */
    object ActionRow : ComponentType(1)

    /**
     * A clickable button.
     */
    object Button : ComponentType(2)

    /**
     * A select menu for picking from choices.
     */
    object SelectMenu : ComponentType(3)

    companion object Serializer : KSerializer<ComponentType> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("ComponentType", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): ComponentType =
            when (val value = decoder.decodeInt()) {
                1 -> ActionRow
                2 -> Button
                3 -> SelectMenu
                else -> Unknown(value)
            }

        override fun serialize(encoder: Encoder, value: ComponentType) = encoder.encodeInt(value.value)
    }
}
