package payload.json

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

internal inline fun <reified T> Json.decodeNotNull(element: JsonElement?) = decodeFromJsonElement<T>(element!!)
