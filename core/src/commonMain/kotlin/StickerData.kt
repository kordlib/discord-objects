package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalSnowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param id id of the sticker
 * @param packId id of the pack the sticker is from
 * @param name name of the sticker
 * @param description description of the sticker
 * @param tags a comma-separated list of tags for the sticker
 * @param asset sticker asset hash
 * @param previewAsset sticker preview asset hash
 * @param formatType type of sticker format
 */
@Serializable
data class StickerData(
    val id: Snowflake,
    @SerialName("pack_id")
    val packId: OptionalSnowflake = OptionalSnowflake.Missing,
    val name: String,
    val description: String?,
    val tags: Optional<String> = Optional.Missing(),
    @Deprecated("Previously the sticker asset hash, now an empty string")
    val asset: String,
    val type: StickerType,
    @SerialName("format_type")
    val formatType: StickerFormatType,
    val available: OptionalBoolean = OptionalBoolean.Missing,
    @SerialName("guild_id")
    val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
    val user: Optional<UserData> = Optional.Missing(),
    @SerialName("sort_value")
    val sortValue: Int,
)
