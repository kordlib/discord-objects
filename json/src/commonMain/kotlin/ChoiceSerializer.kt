package dev.kord.discord.objects

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.*

internal object ChoiceSerializer : JsonContentPolymorphicSerializer<Choice<*>>(Choice::class) {

    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Choice<*>> {
        val value = element.jsonObject.getValue("value").jsonPrimitive
        return when{
            value.isString -> Choice.StringChoice.serializer()
            value.intOrNull != null -> Choice.IntChoice.serializer()
            value.doubleOrNull != null -> Choice.NumberChoice.serializer()
            else -> Choice.StringChoice.serializer()
        }
    }

}
