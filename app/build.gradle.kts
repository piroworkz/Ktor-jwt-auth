@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    war
    alias(libs.plugins.ktor.plugin)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
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
    implementation(libs.client.content.negotiation)
    implementation(libs.client.gson)
}

tasks.jar {
    archiveBaseName.set("V")
    archiveVersion.set("1")
    manifest {
        attributes["Main-Class"] = "io.ktor.server.netty.EngineMain"
    }
    from(sourceSets.main.get().output)
}