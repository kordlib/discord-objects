package dev.kord.discord.objects

import kotlinx.serialization.json.Json

val json = Json {
    serializersModule = jsonModule
    ignoreUnknownKeys = true
    prettyPrint = true
}
