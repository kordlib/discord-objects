package dev.kord.discord.objects.api

interface EnumType<T> {
    val value: T
}

interface EnumTypeCompanion<T : EnumType<*>> {
    val values: Set<T>
}
