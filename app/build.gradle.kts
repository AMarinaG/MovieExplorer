@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("amarinag.android.application")
    id("amarinag.android.application.compose")
    id("amarinag.android.application.jacoco")
    id("amarinag.android.hilt")
    id("jacoco")
}

android {
    namespace = "com.amarinag.movieexplorer"
    defaultConfig {
        applicationId = "com.amarinag.movieexplorer"
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":feature:nowplaying"))
    implementation(project(":feature:favorite"))
    implementation(project(":feature:movie"))

    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))


    androidTestImplementation(project(":core:testing"))
    androidTestImplementation(project(":core:network"))


    androidTestImplementation(kotlin("test"))
    debugImplementation(libs.androidx.compose.ui.testManifest)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.kt)

}

configurations.configureEach {
    resolutionStrategy {
        force(libs.junit4)
        // Temporary workaround for https://issuetracker.google.com/174733673
        force("org.objenesis:objenesis:2.6")
    }
}