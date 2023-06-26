@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("amarinag.android.library")
    id("amarinag.android.library.jacoco")
    id("amarinag.android.hilt")
    id("amarinag.android.room")
}

android {
    namespace = "com.amarinag.core.database"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)

    androidTestImplementation(project(":core:testing"))

}