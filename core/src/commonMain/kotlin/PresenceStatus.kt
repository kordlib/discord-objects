package dev.kord.discord.objects

import dev.kord.discord.objects.api.EnumType
import dev.kord.discord.objects.api.EnumTypeCompanion
import dev.kord.discord.objects.api.serializer.StringEnumTypeSerializer
import kotlinx.serialization.Serializable

@Serializable(with = PresenceStatus.Serializer::class)
sealed class PresenceStatus(override val value: String) : EnumType<String> {

    class Unknown(value: String) : PresenceStatus(value)
    object Online : PresenceStatus("online")
    object Idle : PresenceStatus("idle")
    object DoNotDisturb : PresenceStatus("dnd")
    object Offline : PresenceStatus("offline")
    object Invisible : PresenceStatus("invisible")

    companion object : EnumTypeCompanion<PresenceStatus> {
        override val values: Set<PresenceStatus>
            get() = setOf(Online, Idle, DoNotDisturb, Offline, Invisible)
    }

    internal object Serializer : StringEnumTypeSerializer<PresenceStatus>(
        "PresenceStatus", values, ::Unknown
    )

}
