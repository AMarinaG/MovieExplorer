import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `kotlin-dsl`
}
group = "com.amarinag.movieexplorer.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly("com.android.tools.build:gradle:8.1.0-beta05")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    compileOnly("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.8.20-1.0.11")
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "amarinag.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "amarinag.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "amarinag.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "amarinag.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "amarinag.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}