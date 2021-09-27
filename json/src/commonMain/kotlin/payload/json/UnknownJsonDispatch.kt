package payload.json

import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.EventName
import dev.kord.discord.objects.gateway.payload.UnknownDispatch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

@Serializable(with = UnknownJsonDispatch.Serializer::class)
data class UnknownJsonDispatch(
    @SerialName("d")
    override val data: JsonElement? = null,
    @SerialName("t")
    override val name: EventName,
    @SerialName("s")
    override val sequence: Int
) : UnknownDispatch<JsonElement?>() {


    @OptIn(ExperimentalSerializationApi::class)
    @kotlinx.serialization.Serializer(forClass = UnknownJsonDispatch::class)
    internal object BackingSerializer

    internal object Serializer : JsonTransformingSerializer<UnknownJsonDispatch>(BackingSerializer){

        override fun transformSerialize(element: JsonElement): JsonElement {
            val json = element.jsonObject.toMutableMap()
            json["op"] = JsonPrimitive(Opcode.Dispatch.code)
            return JsonObject(json)
        }

    }

}
