dependencies{
    implementation(libs.server.core)
}

tasks.jar {
    archiveBaseName.set("domain") // Cambia esto para cada módulo
    archiveVersion.set("0.0.1")
    from(sourceSets.main.get().output)
}
