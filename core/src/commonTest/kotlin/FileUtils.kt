package dev.kord.discord.objects

const val RESOURCE_PATH = "./src/commonTest/resources/json/payload"

expect class Resource(name: String) {
    val name: String

    fun exists(): Boolean
    fun readText(): String
}

fun file(name: String) = Resource("$name.json").readText()
