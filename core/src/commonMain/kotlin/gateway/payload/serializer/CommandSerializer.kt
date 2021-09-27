package dev.kord.discord.objects.gateway.payload.serializer

import dev.kord.discord.objects.Snowflake
import dev.kord.discord.objects.gateway.Opcode
import dev.kord.discord.objects.gateway.payload.Command
import dev.kord.discord.objects.gateway.payload.EventName
import kotlinx.serialization.*
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import kotlin.jvm.JvmField
import kotlin.reflect.KClass
import kotlin.reflect.typeOf

@Suppress("UNCHECKED_CAST", "FunctionName")
@OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class)
internal inline fun <reified D : Any, reified T : Command<D>> CommandSerializer(
    noinline constructor: (D) -> T
): KSerializer<T> = CommandSerializer(
    T::class,
    ContextualSerializer(
        D::class,
        serializerOrNull(typeOf<D>()) as? KSerializer<D>,
        CommandSerializer.EMPTY_SERIALIZER_ARRAY
    ),
    constructor
)

internal class CommandSerializer<D : Any, T : Command<D>>(
    klass: KClass<T>,
    private val dataSerializer: KSerializer<D>,
    private val constructor: (D) -> T
) : KSerializer<T> {

    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("dev.kord.discord.objects.payload.${klass.simpleName}") {
            element("op", Opcode.serializer().descriptor, isOptional = false) //opcode
            element("t", EventName.serializer().descriptor, isOptional = true) //event name
            element<Int>("s", isOptional = true) //sequence
            element("d", dataSerializer.descriptor, isOptional = false) //data
        }

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): T {
        lateinit var data: D

        decoder.decodeStructure(descriptor) {
            while (true) {
                when (val index = this.decodeElementIndex(descriptor)) {
                    0 -> decodeSerializableElement(descriptor, index, Snowflake.serializer())

                    //technically we should not expect these types, but Discord marks them as nullable
                    //(even though they are optional), so we'll play ball.
                    1 -> decodeNullableSerializableElement(descriptor, index, String.serializer().nullable)
                    2 -> decodeNullableSerializableElement(descriptor, index, Int.serializer().nullable)

                    3 -> data = decodeSerializableElement(descriptor, index, dataSerializer)
                    CompositeDecoder.DECODE_DONE -> break
                    CompositeDecoder.UNKNOWN_NAME -> throw SerializationException(
                        "unsupported key found in payload"
                    )
                }
            }

            return constructor(data)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: T) {
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, Opcode.serializer(), value.opcode)
            encodeNullableSerializableElement(descriptor, 1, EventName.serializer(), null)
            encodeNullableSerializableElement(descriptor, 2, Int.serializer(), null)
            encodeSerializableElement(descriptor, 3, dataSerializer, value.data)
        }
    }

    internal companion object {

        @JvmField
        internal val EMPTY_SERIALIZER_ARRAY: Array<KSerializer<*>> = arrayOf()
    }
}
