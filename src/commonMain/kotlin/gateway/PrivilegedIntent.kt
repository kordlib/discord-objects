package dev.kord.discord.objects.gateway

private const val PIMessage =
    """
Some intents are defined as "Privileged" due to the sensitive nature of the data and cannot be used by Discord without enabling them.
    
See https://discord.com/developers/docs/topics/gateway#privileged-intents for more info on how to enable these.
"""

/**
 * Some intents are defined as "Privileged" due to the sensitive nature of the data and cannot be used by Discord without enabling them.
 *
 * See [the official documentation](https://discord.com/developers/docs/topics/gateway#privileged-intents) for more info on
 * how to enable these.
 */
@RequiresOptIn(PIMessage, RequiresOptIn.Level.ERROR)
annotation class PrivilegedIntent
