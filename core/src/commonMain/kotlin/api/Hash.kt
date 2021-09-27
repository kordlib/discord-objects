package dev.kord.discord.objects.api

private const val PRIME = 31

/**
 * MPP port of Java's `Objects.hash` implementation. Generates a hashcode from the given [values].
 */
internal fun hash(vararg values: Any?) : Int {
    if (values.isEmpty()) return 0

    var result = 1
    values.forEach {
        result = PRIME * result + it.hashCode()
    }

    return result
}
