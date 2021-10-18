package dev.kord.discord.objects

enum class ActivityFlag(val value: Int) {
    Instance(1),
    Join(2),
    Spectate(4),
    JoinRequest(8),
    Sync(16),
    Play(32)
}
