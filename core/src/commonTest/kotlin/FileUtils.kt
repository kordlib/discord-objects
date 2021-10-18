package dev.kord.discord.objects

const val RESOURCE_PATH = "./src/commonTest/resources/json"

expect class Resource(name: String) {
    val name: String

    fun exists(): Boolean
    fun readText(): String
}

fun payloadFile(name: String) = Resource("payload/$name.json").readText()

fun malformedPayloadFile(name: String) = Resource("malformed/payload/$name.json").readText()
