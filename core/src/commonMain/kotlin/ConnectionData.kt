package dev.kord.discord.objects

import dev.kord.discord.objects.optional.OptionalBoolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A representation of the [Discord Connection Object structure](https://discord.com/developers/docs/resources/user#connection-object).
 * The connection object that the user has attached.
 *
 * @param id The id of the connection account.
 * @param name the username of the connection account.
 * @param type The service of the connection (twitch, youtube).
 * @param revoked Whether the connection is revoked.
 * @param integrations A list of partial server integrations.
 * @param verified Whether the connection is verified.
 * @param friendSync Whether friend sync is enabled for this connection.
 * @param showActivity Whether activities related to this connection will be shown in presence updates.
 * @param visibility The visibility of this connection.
 */
@Serializable
data class ConnectionData(
    val id: String,
    val name: String,
    val type: String,
    val revoked: OptionalBoolean = OptionalBoolean.Missing(),
    val integrations: List<IntegrationData>,
    val verified: Boolean,
    @SerialName("friend_sync")
    val friendSync: Boolean,
    @SerialName("show_activity")
    val showActivity: Boolean,
    val visibility: ConnectionVisibility,
)
