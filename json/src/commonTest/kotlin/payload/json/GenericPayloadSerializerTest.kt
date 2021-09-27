package dev.kord.discord.objects.payload.json

import dev.kord.discord.objects.file
import dev.kord.discord.objects.gateway.payload.Command
import dev.kord.discord.objects.gateway.payload.Dispatch
import dev.kord.discord.objects.gateway.payload.Payload
import dev.kord.discord.objects.json
import kotlinx.serialization.ContextualSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlin.test.Test

@OptIn(ExperimentalSerializationApi::class)
class GenericPayloadSerializerTest {

    @Test
    fun testEncodeRandomDispatchEvent() {
        val event = json.decodeFromString(ContextualSerializer(Dispatch::class), file("channelcreate"))
        json.encodeToString(ContextualSerializer(Dispatch::class), event)
    }

    @Test
    fun testEncodeRandomCommand() {
        val event = json.decodeFromString(ContextualSerializer(Command::class), file("identify"))
        json.encodeToString(ContextualSerializer(Command::class), event)
    }

    @Test
    fun testEncodeRandomPayload() {
        val event = json.decodeFromString(ContextualSerializer(Payload::class), file("channelDelete"))
        json.encodeToString(ContextualSerializer(Payload::class), event)
    }


}
