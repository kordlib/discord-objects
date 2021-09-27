package dev.kord.discord.objects.serializer

import dev.kord.discord.objects.ApplicationCommandOptionType
import dev.kord.discord.objects.CommandArgument
import dev.kord.discord.objects.CommandGroup
import dev.kord.discord.objects.SubCommand
import dev.kord.discord.objects.optional.Optional
import kotlinx.serialization.ContextualSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure

@OptIn(ExperimentalSerializationApi::class)
internal object CommandGroupSerializer : CommandSerializer<CommandGroup, Optional<List<SubCommand>>>(
    "dev.kord.discord.objects.SubCommand",
    Optional.serializer(ListSerializer(SubCommand.serializer()))
) {
    override fun create(name: String, options: Optional<List<SubCommand>>): CommandGroup {
        return CommandGroup(name, options)
    }

    override fun serialize(encoder: Encoder, value: CommandGroup) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.name)
            encodeSerializableElement(descriptor, 1, ApplicationCommandOptionType.serializer(), value.type)
            encodeSerializableElement(descriptor, 2, optionsSerializer, value.options)
        }
    }

}
