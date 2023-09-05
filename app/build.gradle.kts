plugins {
    kotlin("jvm") version "1.8.21"
    id("io.ktor.plugin") version "2.3.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
}

application {
    mainClass.set("io.ktor.server.jetty.EngineMain")
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))
    implementation(libs.arrow.core)
    implementation(libs.commons.codec)
    implementation(libs.bundles.ktor.server)
    implementation(libs.bundles.mongo)
    implementation(libs.bundles.koin)
    implementation("io.ktor:ktor-client-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-gson:2.3.4")
}

tasks.jar {
    archiveBaseName.set("V")
    archiveVersion.set("1")
    manifest {
        attributes["Main-Class"] = "io.ktor.server.netty.EngineMain"
    }
    from(sourceSets.main.get().output)
}