@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("amarinag.android.library")
    id("amarinag.android.library.jacoco")
    id("amarinag.android.hilt")
}

android {
    namespace = "com.amarinag.core.domain"
}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)


    testImplementation(project(":core:testing"))

}