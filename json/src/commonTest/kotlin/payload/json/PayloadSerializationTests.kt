package payload.json

import dev.kord.discord.objects.file
import dev.kord.discord.objects.gateway.payload.*
import dev.kord.discord.objects.json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlin.test.Test
import kotlin.test.assertEquals

class PayloadSerializationTests {

    @Suppress("LocalVariableName")
    private inline fun<reified T: Payload>testEncode(){
        val fileContent = file(T::class.simpleName!!.lowercase())
        val decoded = json.decodeFromString<T>(fileContent)
        val encoded = json.encodeToString(decoded)
        val `re-decoded` = json.decodeFromString<T>(encoded)
        assertEquals(decoded, `re-decoded`)
    }

    @Test
    fun presenceUpdate() = testEncode<PresenceUpdate>()

}
