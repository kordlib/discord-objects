package dev.kord.discord.objects

import dev.kord.discord.objects.api.EnumType
import dev.kord.discord.objects.api.EnumTypeCompanion
import dev.kord.discord.objects.api.serializer.IntEnumTypeSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

/**
 * The state of membership on a Discord developer team.
 */
@Serializable(with = TeamMembershipState.Serializer::class)
sealed class TeamMembershipState(override val value: Int) : EnumType<Int> {

    /**
     * Unknown membership state.
     */
    class Unknown(value: Int) : TeamMembershipState(value)

    /**
     * The user has been invited.
     */
    object Invited : TeamMembershipState(1)

    /**
     * The user has accepted the invitation.
     */
    object Accepted : TeamMembershipState(2)

    companion object : EnumTypeCompanion<TeamMembershipState> {
        override val values: Set<TeamMembershipState>
            get() = setOf(Invited, Accepted)
    }

    @OptIn(ExperimentalSerializationApi::class)
    internal object Serializer : IntEnumTypeSerializer<TeamMembershipState>(
        "TeamMembershipState", values, ::Unknown
    )

}
