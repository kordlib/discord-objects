package dev.kord.discord.objects

import kotlinx.cinterop.*
import platform.posix.*

actual class Resource actual constructor(actual val name: String) {
    private val file = fopen("$RESOURCE_PATH/$name", "r")

    actual fun exists(): Boolean = file != null

    actual fun readText(): String {
        val returnBuffer = StringBuilder()
        val file = file ?: throw IllegalArgumentException("Cannot open input file $name")

        try {
            memScoped {
                val readBufferLength = 64 * 1024
                val buffer = allocArray<ByteVar>(readBufferLength)
                var line = fgets(buffer, readBufferLength, file)?.toKString()
                while (line != null) {
                    returnBuffer.append(line)
                    line = fgets(buffer, readBufferLength, file)?.toKString()
                }
            }
        } finally {
            fclose(file)
        }

        return returnBuffer.toString()
    }
}
