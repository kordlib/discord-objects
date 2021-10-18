package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalInt
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IntegrationData(
    val id: Snowflake,
    val name: String,
    val type: String,
    val enabled: Boolean,
    val syncing: OptionalBoolean = OptionalBoolean.Missing(),
    @SerialName("role_id")
    val roleId: OptionalSnowflake = OptionalSnowflake.Missing,
    @SerialName("enable_emoticons")
    val enableEmoticons: OptionalBoolean = OptionalBoolean.Missing,
    @SerialName("expire_behavior")
    val expireBehavior: Optional<IntegrationExpireBehavior> = Optional.Missing(),
    @SerialName("expire_grace_period")
    val expireGracePeriod: OptionalInt = OptionalInt.Missing,
    val user: Optional<UserData> = Optional.Missing(),
    val account: IntegrationsAccountData,
    @SerialName("synced_at")
    val syncedAt: Optional<String> = Optional.Missing(),
    val subscriberCount: OptionalInt = OptionalInt.Missing,
    val revoked: OptionalBoolean = OptionalBoolean.Missing(),
    val application: Optional<IntegrationApplicationData> = Optional.Missing()
)
