package dev.kord.discord.objects

import dev.kord.discord.objects.api.EnumType
import dev.kord.discord.objects.api.EnumTypeCompanion
import dev.kord.discord.objects.api.serializer.IntEnumTypeSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ActivityType.Serializer::class)
sealed class ActivityType(override val value: Int): EnumType<Int> {
    class Unknown(value: Int) : ActivityType(value)
    object Game: ActivityType(0)
    object Streaming: ActivityType(1)
    object Listening: ActivityType(2)
    object Watching: ActivityType(3)
    object Custom: ActivityType(4)
    object Competing: ActivityType(5)

    companion object: EnumTypeCompanion<ActivityType> {

        override val values: Set<ActivityType>
            get() = setOf(
                Game,
                Streaming,
                Listening,
                Watching,
                Custom,
                Competing
            )

    }

    internal object Serializer : IntEnumTypeSerializer<ActivityType>(
        "ActivityType", values, ::Unknown
    )

}
