@file:Suppress("VulnerableLibrariesLocal")

plugins {
    id("war")
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.arrow.core)

    testImplementation(project(":testShared"))
}

tasks.jar {
    archiveBaseName.set("data")
    archiveVersion.set("0.0.1")
    from(sourceSets.main.get().output)
}
