package dev.kord.discord.objects

import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.OptionalBoolean
import dev.kord.discord.objects.optional.OptionalSnowflake
import dev.kord.discord.objects.serializer.CommandGroupSerializer
import dev.kord.discord.objects.serializer.GenericCommandArgument
import dev.kord.discord.objects.serializer.SubCommandSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class DiscordApplicationCommand(
    val id: Snowflake,
    val type: Optional<ApplicationCommandType> = Optional.Missing(),
    @SerialName("application_id")
    val applicationId: Snowflake,
    val name: String,
    val description: String,
    @SerialName("guild_id")
    val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
    val options: Optional<List<ApplicationCommandOption>> = Optional.Missing(),
    @SerialName("default_permission")
    val defaultPermission: OptionalBoolean = OptionalBoolean.Missing
)

@Serializable(with = ApplicationCommandType.Serializer::class)
sealed class ApplicationCommandType(val value: Int) {
    /** The default code for unknown values. */
    class Unknown(value: Int) : ApplicationCommandType(value)
    object ChatInput : ApplicationCommandType(1)
    object User : ApplicationCommandType(2)
    object Message : ApplicationCommandType(3)
    companion object;

    internal object Serializer : KSerializer<ApplicationCommandType> {
        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("type", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): ApplicationCommandType = when (val code = decoder.decodeInt()) {
            1 -> ChatInput
            2 -> User
            3 -> Message
            else -> Unknown(code)
        }

        override fun serialize(encoder: Encoder, value: ApplicationCommandType) = encoder.encodeInt(value.value)
    }

}

@Serializable
class ApplicationCommandOption(
    val type: ApplicationCommandOptionType,
    val name: String,
    val description: String,
    val default: OptionalBoolean = OptionalBoolean.Missing,
    val required: OptionalBoolean = OptionalBoolean.Missing,
    val choices: Optional<List<@Contextual Choice<@Serializable(with = NotSerializable::class) Any?>>> = Optional.Missing(),
    val options: Optional<List<ApplicationCommandOption>> = Optional.Missing(),
)

/**
 * A serializer whose sole purpose is to provide a No-Op serializer for [Any].
 * The serializer is used when the generic type is neither known nor relevant to the serialization process
 *
 * e.g: `Choice<@Serializable(NotSerializable::class) Any?>`
 * The serialization is handled by [Choice] serializer instead where we don't care about the generic type.
 */
internal object NotSerializable : KSerializer<Any?> {
    override fun deserialize(decoder: Decoder) = error("This operation is not supported.")
    override val descriptor: SerialDescriptor = String.serializer().descriptor
    override fun serialize(encoder: Encoder, value: Any?) = error("This operation is not supported.")
}


@Serializable(ApplicationCommandOptionType.Serializer::class)
sealed class ApplicationCommandOptionType(val type: Int) {

    object SubCommand : ApplicationCommandOptionType(1)
    object SubCommandGroup : ApplicationCommandOptionType(2)
    object String : ApplicationCommandOptionType(3)
    object Integer : ApplicationCommandOptionType(4)
    object Boolean : ApplicationCommandOptionType(5)
    object User : ApplicationCommandOptionType(6)
    object Channel : ApplicationCommandOptionType(7)
    object Role : ApplicationCommandOptionType(8)
    object Mentionable : ApplicationCommandOptionType(9)
    object Number : ApplicationCommandOptionType(10)
    class Unknown(type: Int) : ApplicationCommandOptionType(type)

    companion object {
        val values: Set<ApplicationCommandOptionType>
            get() = setOf(
                SubCommand,
                SubCommandGroup,
                String,
                Integer,
                Boolean,
                User,
                Channel,
                Role,
                Mentionable,
                Number
            )
    }

    internal object Serializer : KSerializer<ApplicationCommandOptionType> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("ApplicationCommandOptionType", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): ApplicationCommandOptionType {
            return when (val type = decoder.decodeInt()) {
                1 -> SubCommand
                2 -> SubCommandGroup
                3 -> String
                4 -> Integer
                5 -> Boolean
                6 -> User
                7 -> Channel
                8 -> Role
                9 -> Mentionable
                10 -> Number
                else -> Unknown(type)
            }
        }

        override fun serialize(encoder: Encoder, value: ApplicationCommandOptionType) {
            encoder.encodeInt(value.type)
        }
    }

}

sealed class Choice<out T> {
    abstract val name: String
    abstract val value: T

    @Serializable
    data class IntChoice(override val name: String, override val value: Int) : Choice<Int>()

    @Serializable
    data class NumberChoice(override val name: String, override val value: Double) : Choice<Double>()

    @Serializable
    data class StringChoice(override val name: String, override val value: String) : Choice<String>()

}

@Serializable
data class ResolvedObjects(
    val members: Optional<Map<Snowflake, DiscordGuildMember>> = Optional.Missing(),
    val users: Optional<Map<Snowflake, DiscordUser>> = Optional.Missing(),
    val roles: Optional<Map<Snowflake, DiscordRole>> = Optional.Missing(),
    val channels: Optional<Map<Snowflake, DiscordChannel>> = Optional.Missing(),
    val messages: Optional<Map<Snowflake, DiscordMessage>> = Optional.Missing()
)

@Serializable
data class DiscordInteraction(
    val id: Snowflake,
    @SerialName("application_id")
    val applicationId: Snowflake,
    val data: InteractionCallbackData,
    @SerialName("guild_id")
    val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
    @SerialName("channel_id")
    val channelId: Snowflake,
    val member: Optional<DiscordInteractionGuildMember> = Optional.Missing(),
    val user: Optional<DiscordUser> = Optional.Missing(),
    val token: String,
    val version: Int,
    val message: Optional<DiscordMessage> = Optional.Missing(),
    val type: InteractionType
)

@Serializable(InteractionType.Serializer::class)
sealed class InteractionType(val type: Int) {
    object Ping : InteractionType(1)
    object ApplicationCommand : InteractionType(2)

    /*
     * don't trust the docs:
     *
     * this type exists and is needed for components even though it's not documented
     */
    object Component : InteractionType(3)
    class Unknown(type: Int) : InteractionType(type)

    override fun toString(): String = when (this) {
        Ping -> "InteractionType.Ping($type)"
        ApplicationCommand -> "InteractionType.ApplicationCommand($type)"
        Component -> "InteractionType.ComponentInvoke($type)"
        is Unknown -> "InteractionType.Unknown($type)"
    }

    companion object;
    internal object Serializer : KSerializer<InteractionType> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("InteractionType", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): InteractionType {
            return when (val type = decoder.decodeInt()) {
                1 -> Ping
                2 -> ApplicationCommand
                3 -> Component
                else -> Unknown(type)
            }
        }

        override fun serialize(encoder: Encoder, value: InteractionType) {
            encoder.encodeInt(value.type)
        }

    }
}

@Serializable
data class InteractionCallbackData(
    val id: OptionalSnowflake = OptionalSnowflake.Missing,
    val type: Optional<ApplicationCommandType> = Optional.Missing(),
    @SerialName("target_id")
    val targetId: OptionalSnowflake = OptionalSnowflake.Missing,
    val name: Optional<String> = Optional.Missing(),
    val resolved: Optional<ResolvedObjects> = Optional.Missing(),
    val options: Optional<List<@Contextual Option>> = Optional.Missing(),
    @SerialName("custom_id")
    val customId: Optional<String> = Optional.Missing(),
    @SerialName("component_type")
    val componentType: Optional<ComponentType> = Optional.Missing(),
    val values: Optional<List<String>> = Optional.Missing(),
)

sealed class Option {
    abstract val name: String
    abstract val type: ApplicationCommandOptionType

//    internal object Serializer : KSerializer<Option> {
//
//        override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Kord.Option") {
//            element("name", String.serializer().descriptor, isOptional = false)
//            element("value", JsonElement.serializer().descriptor, isOptional = true)
//            element("options", JsonArray.serializer().descriptor, isOptional = true)
//            element("type", ApplicationCommandOptionType.serializer().descriptor, isOptional = false)
//        }
//
//        override fun deserialize(decoder: Decoder): Option {
//            decoder as? JsonDecoder ?: error("Option can only be deserialize with a JsonDecoder")
//            val json = decoder.json
//
//            var name = ""
//            var jsonValue: JsonElement? = null
//            var jsonOptions: JsonArray? = null
//            var type: ApplicationCommandOptionType? = null
//            decoder.decodeStructure(descriptor) {
//                while (true) {
//                    when (val index = decodeElementIndex(descriptor)) {
//                        0 -> name = decodeStringElement(descriptor, index)
//                        1 -> jsonValue = decodeSerializableElement(descriptor, index, JsonElement.serializer())
//                        2 -> jsonOptions = decodeSerializableElement(descriptor, index, JsonArray.serializer())
//                        3 -> type =
//                            decodeSerializableElement(descriptor, index, ApplicationCommandOptionType.serializer())
//
//                        CompositeDecoder.DECODE_DONE -> return@decodeStructure
//                        else -> throw SerializationException("unknown index: $index")
//                    }
//                }
//            }
//
//            requireNotNull(type) { "'type' expected for $name but was absent" }
//
//            return when (type) {
//                ApplicationCommandOptionType.SubCommand -> {
//                    val options = if (jsonOptions == null) Optional.Missing()
//                    else Optional.Value(jsonOptions!!.map {
//                        json.decodeFromJsonElement(serializer(), it) as CommandArgument<*>
//                    })
//
//                    SubCommand(name, options)
//                }
//
//                ApplicationCommandOptionType.SubCommandGroup -> {
//                    val options = if (jsonOptions == null) Optional.Missing()
//                    else Optional.Value(jsonOptions!!.map {
//                        json.decodeFromJsonElement(serializer(), it) as SubCommand
//                    })
//
//                    CommandGroup(name, options)
//                }
//                ApplicationCommandOptionType.Boolean,
//                ApplicationCommandOptionType.Channel,
//                ApplicationCommandOptionType.Integer,
//                ApplicationCommandOptionType.Number,
//                ApplicationCommandOptionType.Mentionable,
//                ApplicationCommandOptionType.Role,
//                ApplicationCommandOptionType.String,
//                ApplicationCommandOptionType.User -> CommandArgument.Serializer.deserialize(
//                    json, jsonValue!!, name, type!!
//                )
//                else -> error("unknown ApplicationCommandOptionType $type")
//            }
//        }
//
//        override fun serialize(encoder: Encoder, value: Option) {
//            when (value) {
//                is CommandArgument<*> -> CommandArgument.Serializer.serialize(encoder, value)
//                is CommandGroup -> encoder.encodeStructure(descriptor) {
//                    encodeSerializableElement(
//                        descriptor, 0, String.serializer(), value.name
//                    )
//                    encodeSerializableElement(
//                        descriptor, 2, OptionalSerializer(ListSerializer(Serializer)), value.options
//                    )
//
//                    encodeSerializableElement(
//                        descriptor, 3, ApplicationCommandOptionType.serializer(), value.type
//                    )
//                }
//                is SubCommand -> encoder.encodeStructure(descriptor) {
//                    encodeSerializableElement(
//                        descriptor, 0, String.serializer(), value.name
//                    )
//                    encodeSerializableElement(
//                        descriptor, 2, OptionalSerializer(ListSerializer(Serializer)), value.options
//                    )
//
//                    encodeSerializableElement(
//                        descriptor, 3, ApplicationCommandOptionType.serializer(), value.type
//                    )
//                }
//            }
//        }
//    }
}

@Serializable(with = SubCommandSerializer::class)
data class SubCommand(
    override val name: String,
    val options: Optional<List<CommandArgument<Any?>>> = Optional.Missing()
) : Option() {

    override val type: ApplicationCommandOptionType
        get() = ApplicationCommandOptionType.SubCommand
}

sealed class CommandArgument<out T> : Option() {

    abstract val value: T

    @Serializable(with = StringArgument.Serializer::class)
    data class StringArgument(
        override val name: String,
        override val value: String
    ) : CommandArgument<String>() {
        override val type: ApplicationCommandOptionType
            get() = ApplicationCommandOptionType.String

        internal object Serializer : KSerializer<StringArgument> by GenericCommandArgument(::StringArgument)
    }

    @Serializable(with = IntegerArgument.Serializer::class)
    data class IntegerArgument(
        override val name: String,
        override val value: Long
    ) : CommandArgument<Long>() {
        override val type: ApplicationCommandOptionType
            get() = ApplicationCommandOptionType.Integer

        internal object Serializer : KSerializer<IntegerArgument> by GenericCommandArgument(::IntegerArgument)
    }

    @Serializable(with = NumberArgument.Serializer::class)
    class NumberArgument(
        override val name: String,
        override val value: Double
    ) : CommandArgument<Double>() {
        override val type: ApplicationCommandOptionType
            get() = ApplicationCommandOptionType.Number

        internal object Serializer : KSerializer<NumberArgument> by GenericCommandArgument(::NumberArgument)
    }

    @Serializable(with = BooleanArgument.Serializer::class)
    data class BooleanArgument(
        override val name: String,
        override val value: Boolean
    ) : CommandArgument<Boolean>() {
        override val type: ApplicationCommandOptionType
            get() = ApplicationCommandOptionType.Boolean

        internal object Serializer : KSerializer<BooleanArgument> by GenericCommandArgument(::BooleanArgument)
    }

    @Serializable(with = UserArgument.Serializer::class)
    data class UserArgument(
        override val name: String,
        override val value: Snowflake
    ) : CommandArgument<Snowflake>() {
        override val type: ApplicationCommandOptionType
            get() = ApplicationCommandOptionType.User

        internal object Serializer : KSerializer<UserArgument> by GenericCommandArgument(::UserArgument)
    }

    @Serializable(with = ChannelArgument.Serializer::class)
    data class ChannelArgument(
        override val name: String,
        override val value: Snowflake
    ) : CommandArgument<Snowflake>() {
        override val type: ApplicationCommandOptionType
            get() = ApplicationCommandOptionType.Channel

        internal object Serializer : KSerializer<ChannelArgument> by GenericCommandArgument(::ChannelArgument)
    }

    @Serializable(with = RoleArgument.Serializer::class)
    data class RoleArgument(
        override val name: String,
        override val value: Snowflake
    ) : CommandArgument<Snowflake>() {
        override val type: ApplicationCommandOptionType
            get() = ApplicationCommandOptionType.Role

        internal object Serializer : KSerializer<RoleArgument> by GenericCommandArgument(::RoleArgument)
    }

    @Serializable(with = MentionableArgument.Serializer::class)
    data class MentionableArgument(
        override val name: String,
        override val value: Snowflake
    ) : CommandArgument<Snowflake>() {
        override val type: ApplicationCommandOptionType
            get() = ApplicationCommandOptionType.Mentionable

        internal object Serializer : KSerializer<MentionableArgument> by GenericCommandArgument(::MentionableArgument)
    }

}

@Serializable(with = CommandGroupSerializer::class)
data class CommandGroup(
    override val name: String,
    val options: Optional<List<SubCommand>> = Optional.Missing(),
) : Option() {
    override val type: ApplicationCommandOptionType
        get() = ApplicationCommandOptionType.SubCommandGroup
}

fun CommandArgument<*>.int(): Long {
    return value as? Long ?: error("$value wasn't an int.")
}


fun CommandArgument<*>.string(): String {
    return value.toString()
}


fun CommandArgument<*>.boolean(): Boolean {
    return value as? Boolean ?: error("$value wasn't a Boolean.")
}


fun CommandArgument<*>.snowflake(): Snowflake {
    val id = string().toULongOrNull() ?: error("$value wasn't a Snowflake")
    return Snowflake(id)
}

@Serializable(InteractionResponseType.Serializer::class)
sealed class InteractionResponseType(val type: Int) {
    object Pong : InteractionResponseType(1)
    object ChannelMessageWithSource : InteractionResponseType(4)
    object DeferredChannelMessageWithSource : InteractionResponseType(5)
    object DeferredUpdateMessage : InteractionResponseType(6)
    object UpdateMessage : InteractionResponseType(7)
    class Unknown(type: Int) : InteractionResponseType(type)

    companion object;

    internal object Serializer : KSerializer<InteractionResponseType> {

        override val descriptor: SerialDescriptor
            get() = PrimitiveSerialDescriptor("InteractionResponseType", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): InteractionResponseType {
            return when (val type = decoder.decodeInt()) {
                1 -> Pong
                4 -> ChannelMessageWithSource
                5 -> DeferredChannelMessageWithSource
                6 -> DeferredUpdateMessage
                7 -> UpdateMessage
                else -> Unknown(type)
            }
        }

        override fun serialize(encoder: Encoder, value: InteractionResponseType) {
            encoder.encodeInt(value.type)
        }
    }
}


@Serializable
data class DiscordGuildApplicationCommandPermissions(
    val id: Snowflake,
    @SerialName("application_id")
    val applicationId: Snowflake,
    @SerialName("guild_id")
    val guildId: Snowflake,
    val permissions: List<DiscordGuildApplicationCommandPermission>
)


@Serializable
data class PartialDiscordGuildApplicationCommandPermissions(
    val id: Snowflake,
    val permissions: List<DiscordGuildApplicationCommandPermission>
)


@Serializable
data class DiscordGuildApplicationCommandPermission(
    val id: Snowflake,
    val type: Type,
    val permission: Boolean
) {
    @Serializable(with = Type.Serializer::class)
    sealed class Type(val value: Int) {
        object Role : Type(1)
        object User : Type(2)
        class Unknown(value: Int) : Type(value)

        object Serializer : KSerializer<Type> {
            override val descriptor: SerialDescriptor =
                PrimitiveSerialDescriptor("type", PrimitiveKind.INT)

            override fun deserialize(decoder: Decoder): Type =
                when (val value = decoder.decodeInt()) {
                    1 -> Role
                    2 -> User
                    else -> Unknown(value)
                }

            override fun serialize(encoder: Encoder, value: Type) = encoder.encodeInt(value.value)
        }
    }
}
