@file:Suppress("VulnerableLibrariesLocal")

dependencies {
    implementation(project(":domain"))
    implementation(libs.arrow.core)
}

tasks.jar {
    archiveBaseName.set("data")
    archiveVersion.set("0.0.1")
    from(sourceSets.main.get().output)
}
