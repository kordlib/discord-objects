package dev.kord.discord.objects.payload.json

import dev.kord.discord.objects.gateway.payload.Command
import dev.kord.discord.objects.gateway.payload.Dispatch
import dev.kord.discord.objects.gateway.payload.Payload
import dev.kord.discord.objects.json
import dev.kord.discord.objects.malformedPayloadFile
import dev.kord.discord.objects.payloadFile
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test
import kotlin.test.assertFailsWith

@OptIn(ExperimentalSerializationApi::class)
class GenericPayloadSerializerTest {

    @Test
    fun testEncodeRandomDispatchEvent() {
        val event = json.decodeFromString(Dispatch.genericSerializer(), payloadFile("channelcreate"))
        val string = json.encodeToString(Dispatch.genericSerializer(), event)
        json.decodeFromString(Dispatch.genericSerializer(), string)
    }

    @Test
    fun testEncodeRandomCommand() {
        val event = json.decodeFromString(Command.genericSerializer(), payloadFile("identify"))
        val string = json.encodeToString(Command.genericSerializer(), event)
        json.decodeFromString(Command.genericSerializer(), string)
    }

    @Test
    fun testEncodeRandomPayload() {
        val event = json.decodeFromString(Payload.serializer(), payloadFile("channelDelete"))
        val string = json.encodeToString(Payload.serializer(), event)
        json.decodeFromString(Payload.serializer(), string)
    }

    @Test
    fun testEncodePayloadWithDFieldFirstFails() {
        assertFailsWith<IllegalArgumentException> {
            json.decodeFromString(Payload.serializer(), malformedPayloadFile("dFirst"))
        }
    }

    @Test
    fun testEncodeDispatchWithWrongNameFails() {
        assertFailsWith<IllegalArgumentException> {
            json.decodeFromString(Dispatch.genericSerializer(), malformedPayloadFile("wrongName"))
        }
    }

    @Test
    fun testEncodeDispatchWithWrongOpFails() {
        assertFailsWith<IllegalArgumentException> {
            json.decodeFromString(Dispatch.genericSerializer(), malformedPayloadFile("wrongOp"))
        }
    }

}
