package dev.kord.discord.objects

enum class UserFlag(val code: Int) {
    None(0),
    DiscordEmployee(1 shl 0),
    DiscordPartner(1 shl 1),
    HypeSquad(1 shl 2),
    BugHunterLevel1(1 shl 3),
    HouseBravery(1 shl 6),
    HouseBrilliance(1 shl 7),
    HouseBalance(1 shl 8),
    EarlySupporter(1 shl 9),
    TeamUser(1 shl 10),
    System(1 shl 12),
    BugHunterLevel2(1 shl 14),
    VerifiedBot(1 shl 16),
    VerifiedBotDeveloper(1 shl 17)
}
