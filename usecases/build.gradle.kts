@file:Suppress("VulnerableLibrariesLocal")

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(libs.server.core)
    implementation(libs.arrow.core)

    testImplementation(libs.server.tests)
    testImplementation(libs.kotlin.test.junit)
}