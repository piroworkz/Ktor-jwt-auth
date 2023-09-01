@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm") version libs.versions.kotlin apply false
}

allprojects {
    group = "davidluna.com"
    version = "0.0.1"
}

subprojects {
    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
    }

    afterEvaluate {
        dependencies {
            "testImplementation"(kotlin("test", libs.versions.kotlin.get()))
        }
    }
}
