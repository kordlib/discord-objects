package dev.kord.discord.objects

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.*

internal object ChoiceSerializer : JsonContentPolymorphicSerializer<ApplicationCommandOptionChoiceData<*>>(ApplicationCommandOptionChoiceData::class) {

    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out ApplicationCommandOptionChoiceData<*>> {
        val value = element.jsonObject.getValue("value").jsonPrimitive
        return when{
            value.isString -> ApplicationCommandOptionChoiceData.String.serializer()
            value.intOrNull != null -> ApplicationCommandOptionChoiceData.Int.serializer()
            value.doubleOrNull != null -> ApplicationCommandOptionChoiceData.Double.serializer()
            else -> ApplicationCommandOptionChoiceData.String.serializer()
        }
    }

}
