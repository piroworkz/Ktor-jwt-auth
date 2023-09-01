plugins {
    kotlin("jvm") version "1.8.21"
    id("io.ktor.plugin") version "2.3.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
    application
}


application {
    mainClass.set("io.ktor.server.netty.EngineMain")
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))
    implementation(libs.server.core)
    implementation(libs.server.auth)
    implementation(libs.server.auth.jwt)
    implementation(libs.server.resources)
    implementation(libs.server.swagger)
    implementation(libs.server.call.logging)
    implementation(libs.server.netty)
    implementation(libs.server.content.negotiation)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.logback.classic)
    implementation(libs.arrow.core)
    implementation(libs.commons.codec)
    implementation(libs.mongo.db.driver)
    implementation(libs.mongo.bson)
    implementation(libs.koin.di)
    implementation(libs.koin.logger)
    implementation(libs.javax.inject)

    testImplementation(libs.server.tests)
    testImplementation(libs.kotlin.test.junit)
}