@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("amarinag.jvm.library")
}

dependencies {
    implementation(libs.kotlinx.datetime)
}