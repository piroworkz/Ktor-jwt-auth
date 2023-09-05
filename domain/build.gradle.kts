tasks.jar {
    archiveBaseName.set("domain")
    archiveVersion.set("0.0.1")
    from(sourceSets.main.get().output)
}
