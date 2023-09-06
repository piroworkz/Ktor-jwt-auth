@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm") version "1.8.21" apply false
}

allprojects {
    group = "davidluna.com"
    version = "0.0.1"
}

subprojects {
    plugins.apply("kotlin")
    repositories {
        mavenCentral()
    }
    afterEvaluate {
        dependencies.apply {
            add("testImplementation", libs.bundles.server.tests)
        }
    }
}