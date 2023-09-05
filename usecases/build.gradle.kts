@file:Suppress("VulnerableLibrariesLocal")

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(libs.arrow.core)
}

tasks.jar {
    archiveBaseName.set("usecases") // Cambia esto para cada módulo
    archiveVersion.set("0.0.1")
    from(sourceSets.main.get().output)
}
