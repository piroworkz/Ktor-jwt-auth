@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm") version libs.versions.kotlin apply false
}

allprojects {
    group = "davidluna.com"
    version = "0.0.1"
}

subprojects {
    plugins.apply("kotlin")
    plugins.apply("war")
    repositories {
        mavenCentral()
    }

    afterEvaluate {
        dependencies.apply {
            add("testImplementation", libs.bundles.server.tests)
        }
    }
}