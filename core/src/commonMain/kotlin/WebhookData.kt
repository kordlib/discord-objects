package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A representation of the [Discord Webhook structure](https://discord.com/developers/docs/resources/webhook#webhook-object).
 *
 * @param id The id of the webhook.
 * @param type The type of the webhook.
 * @param guildId The guild id this webhook is for.
 * @param channelId The channel id this webhook is for.
 * @param user The user this webhook was created by (not present when getting a webhook with its [token]).
 * @param name The default name of the webhook.
 * @param avatar The default avatar of the webhook.
 * @param token The secure token of this webhook (returned for [incoming webhooks][WebhookType.Incoming]).
 * @param applicationId The bot/OAuth2 application that created this webhook.
 */
@Serializable
data class WebhookData(
    val id: Snowflake,
    val type: WebhookType,
    @SerialName("guild_id")
    val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
    @SerialName("channel_id")
    val channelId: Snowflake,
    val user: Optional<UserData> = Optional.Missing(),
    val name: String?,
    val avatar: String?,
    val token: Optional<String> = Optional.Missing(),
    @SerialName("application_id")
    val applicationId: Snowflake?,
)
