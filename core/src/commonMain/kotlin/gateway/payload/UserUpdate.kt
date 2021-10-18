package dev.kord.discord.objects.gateway.payload

import dev.kord.discord.objects.UserData
import dev.kord.discord.objects.gateway.payload.serializer.DispatchSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = UserUpdate.Serializer::class)
data class UserUpdate(
    override val data: UserData,
    override val sequence: Int
) : Dispatch<UserData>() {
    override val name: EventName get() = EventName.UserUpdate
    
    internal object Serializer : KSerializer<UserUpdate> by DispatchSerializer(::UserUpdate)
}
