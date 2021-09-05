@file:JvmName("ColorExtensions")

package dev.kord.discord.objects.api

val java.awt.Color.kColor get() = Color(rgb)

fun Color(color: java.awt.Color): Color = Color(color.rgb)
