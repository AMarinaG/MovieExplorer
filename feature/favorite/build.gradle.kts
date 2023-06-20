@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("amarinag.android.feature")
    id("amarinag.android.library.compose")
}

android {
    namespace = "com.amarinag.feature.favorite"
}

dependencies {
    implementation(libs.androidx.compose.material3.windowSizeClass)
    androidTestImplementation(libs.androidx.test.ext)

}