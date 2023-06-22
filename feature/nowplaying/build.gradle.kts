@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("amarinag.android.feature")
    id("amarinag.android.library.compose")
}

android {
    namespace = "com.amarinag.feature.nowplaying"
}

dependencies {
    androidTestImplementation(libs.androidx.test.ext)
}