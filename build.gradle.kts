plugins {
    kotlin("multiplatform") version "1.5.0"
}

version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    macosX64 {
        binaries {
            executable()
        }
    }
    linuxX64 {
        binaries {
            executable()
        }
    }
    mingwX64 {
        binaries {
            executable()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
        val desktopMain by creating {
            dependsOn(commonMain)
        }
        val linuxX64Main by getting {
            dependsOn(desktopMain)
        }
        val mingwX64Main by getting {
            dependsOn(desktopMain)
        }
        val macosX64Main by getting {
            dependsOn(desktopMain)
        }
    }
}
