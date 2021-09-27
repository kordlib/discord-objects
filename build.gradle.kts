plugins {
    kotlin("multiplatform") version "1.5.20" apply false
    kotlin("plugin.serialization") version "1.5.20" apply false
}

val rootGroup = "dev.kord"
val rootVersion = "0.0.1"

allprojects {
    this.group = rootGroup
    this.version = rootVersion

    repositories {
        mavenCentral()
    }
}
