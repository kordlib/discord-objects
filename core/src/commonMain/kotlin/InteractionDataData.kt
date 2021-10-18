package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InteractionDataData(
    val id: Snowflake,
    val name: String,
    val type: ApplicationCommandType,
    val resolved: Optional<ResolvedData> = Optional.Missing(),
    val options: Optional<List<ApplicationCommandInteractionDataOptionData>> = Optional.Missing(),
    @SerialName("target_id")
    val targetId: OptionalSnowflake = OptionalSnowflake.Missing,
    @SerialName("custom_id")
    val customId: Optional<String> = Optional.Missing(),
    @SerialName("component_type")
    val componentType: Optional<ComponentType> = Optional.Missing(),
    val values: Optional<List<String>> = Optional.Missing(),
)
