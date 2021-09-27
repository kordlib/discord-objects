package dev.kord.discord.objects

import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
}
