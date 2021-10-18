package dev.kord.discord.objects

import dev.kord.discord.objects.gateway.payload.serializer.decodeSnowflake
import dev.kord.discord.objects.gateway.payload.serializer.encodeSnowflake
import dev.kord.discord.objects.optional.Optional
import dev.kord.discord.objects.optional.optional
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable(with = ApplicationCommandInteractionDataOptionData.Serializer::class)
data class ApplicationCommandInteractionDataOptionData(
    val name: kotlin.String,
    val type: ApplicationCommandOptionType,
    val value: Optional<ApplicationCommandOptionTypeData<*>> = Optional.Missing(),
    val options: Optional<List<ApplicationCommandInteractionDataOptionData>> = Optional.Missing(),
) {

    internal object Serializer : KSerializer<ApplicationCommandInteractionDataOptionData> {

        @OptIn(ExperimentalSerializationApi::class, kotlinx.serialization.InternalSerializationApi::class)
        override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
            "dev.kord.discord.objects.ApplicationCommandInteractionDataOptionData"
        ) {
            element<String>("name")
            element("type", ApplicationCommandType.serializer().descriptor)
            element(
                "value",
                buildSerialDescriptor("value", SerialKind.CONTEXTUAL),
                isOptional = true
            )
            element(
                "options",
                buildSerialDescriptor("options", SerialKind.CONTEXTUAL),
                isOptional = true
            )
        }

        @OptIn(ExperimentalSerializationApi::class)
        override fun serialize(
            encoder: Encoder,
            value: ApplicationCommandInteractionDataOptionData,
        ) = encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.name)
            encodeSerializableElement(descriptor, 1, ApplicationCommandOptionType.serializer(), value.type)
            when (val wrapper = value.value.value) {
                is ApplicationCommandOptionTypeData.Boolean -> encodeBooleanElement(descriptor, 2, wrapper.value)
                is ApplicationCommandOptionTypeData.Channel -> encodeSnowflake(descriptor, 2, wrapper.value)
                is ApplicationCommandOptionTypeData.Mentionable -> encodeSnowflake(descriptor, 2, wrapper.value)
                is ApplicationCommandOptionTypeData.Integer -> encodeLongElement(descriptor, 2, wrapper.value)
                is ApplicationCommandOptionTypeData.Number -> encodeDoubleElement(descriptor, 2, wrapper.value)
                is ApplicationCommandOptionTypeData.Role -> encodeSnowflake(descriptor, 2, wrapper.value)
                is ApplicationCommandOptionTypeData.String -> encodeStringElement(descriptor, 2, wrapper.value)
                is ApplicationCommandOptionTypeData.User -> encodeSnowflake(descriptor, 2, wrapper.value)
                is ApplicationCommandOptionTypeData.Unknown<*> -> throw SerializationException("ApplicationCommandOptionTypeData.Unknown cannot be serialized")
                null -> encodeNullableSerializableElement(descriptor, 2, Unit.serializer().nullable, null)
            }
            encodeSerializableElement(descriptor, 3, Optional.serializer(ListSerializer(serializer())), value.options)
        }

        override fun deserialize(
            decoder: Decoder,
        ): ApplicationCommandInteractionDataOptionData = decoder.decodeStructure(descriptor) {
            var name: String? = null
            var type: ApplicationCommandOptionType? = null
            var value: Optional<ApplicationCommandOptionTypeData<*>> = Optional.Missing()
            var options: Optional<List<ApplicationCommandInteractionDataOptionData>> = Optional.Missing()

            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> name = decodeStringElement(descriptor, index)
                    1 -> {
                        type = decodeSerializableElement(descriptor, index, ApplicationCommandOptionType.serializer())
                        if (type is ApplicationCommandOptionType.Unknown) throw SerializationException(
                            "$type cannot be deserialized"
                        )
                    }
                    2 -> {
                        requireNotNull(type) { "'type' field is required before 'value' field" }
                        value = when (type) {
                            ApplicationCommandOptionType.Boolean -> ApplicationCommandOptionTypeData.Boolean(
                                decodeBooleanElement(descriptor, index)
                            )
                            ApplicationCommandOptionType.Channel -> ApplicationCommandOptionTypeData.Channel(
                                decodeSnowflake(descriptor, index)
                            )
                            ApplicationCommandOptionType.Integer -> ApplicationCommandOptionTypeData.Integer(
                                decodeLongElement(descriptor, index)
                            )
                            ApplicationCommandOptionType.Mentionable -> ApplicationCommandOptionTypeData.Mentionable(
                                decodeSnowflake(descriptor, index)
                            )
                            ApplicationCommandOptionType.Number -> ApplicationCommandOptionTypeData.Number(
                                decodeDoubleElement(descriptor, index)
                            )
                            ApplicationCommandOptionType.Role -> ApplicationCommandOptionTypeData.Role(
                                decodeSnowflake(descriptor,index)
                            )
                            ApplicationCommandOptionType.String -> ApplicationCommandOptionTypeData.String(
                                decodeStringElement(descriptor, index)
                            )
                            ApplicationCommandOptionType.User -> ApplicationCommandOptionTypeData.User(
                                decodeSnowflake(descriptor,index)
                            )
                            ApplicationCommandOptionType.SubCommand -> throw SerializationException(
                                "SubCommand should not have a 'value' field"
                            )
                            ApplicationCommandOptionType.SubCommandGroup -> throw SerializationException(
                                "SubCommandGroup should not have a 'value' field"
                            )
                            is ApplicationCommandOptionType.Unknown -> throw SerializationException(
                                "Cannot deserialize value for 'type' with value $type"
                            )
                        }.optional()
                    }
                    3 -> options = decodeSerializableElement(
                        descriptor, index, Optional.serializer(ListSerializer(serializer()))
                    )

                    CompositeDecoder.DECODE_DONE -> break
                }
            }


            requireNotNull(name) { "'name' field is required but was missing" }
            requireNotNull(type) { "'type' field is required bt was missing" }

            return ApplicationCommandInteractionDataOptionData(
                name,
                type,
                value,
                options
            )
        }

    }

}
