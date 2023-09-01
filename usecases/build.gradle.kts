@file:Suppress("VulnerableLibrariesLocal")

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(libs.server.core)
    implementation(libs.arrow.core)

    testImplementation(libs.server.tests)
    testImplementation(libs.kotlin.test.junit)
}

tasks.jar {
    archiveBaseName.set("usecases") // Cambia esto para cada módulo
    archiveVersion.set("0.0.1")
    from(sourceSets.main.get().output)
}
