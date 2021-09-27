package dev.kord.discord.objects

import dev.kord.discord.objects.payload.json.serializer.DispatchSerializer
import dev.kord.discord.objects.api.JsonRawData
import dev.kord.discord.objects.api.RawData
import dev.kord.discord.objects.gateway.payload.*
import dev.kord.discord.objects.payload.json.serializer.CommandSerializer
import dev.kord.discord.objects.payload.json.serializer.PayloadSerializer
import kotlinx.serialization.modules.*
import payload.json.UnknownJsonDispatch
import payload.json.UnknownJsonEvent

public val jsonModule = SerializersModule {
    contextual(DispatchSerializer)
    contextual(CommandSerializer)
    contextual(PayloadSerializer)

    //a bit of a hack to get around the type system
    //but our own implementations *should* be the only ones we'll ever interact with
    contextual(RawData::class) { JsonRawData.serializer() }
    contextual(UnknownEvent::class) { UnknownJsonEvent.serializer() }
    contextual(UnknownDispatch::class) { UnknownJsonDispatch.serializer() }

    //other contextual serializers
    contextual(DiscordPresenceUserSerializer)
    contextual(ChoiceSerializer)
    contextual(AuditLogChangeSerializer)
    contextual(CommandArgumentSerializer)
    contextual(OptionSerializer)
}
