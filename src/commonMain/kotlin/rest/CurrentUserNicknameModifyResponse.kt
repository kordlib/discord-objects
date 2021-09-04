package dev.kord.discord.objects.rest

import kotlinx.serialization.Serializable

@Serializable
data class CurrentUserNicknameModifyResponse(val nick: String)
