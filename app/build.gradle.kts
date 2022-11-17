plugins {
    id("com.android.application")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.10"
}

android {
    compileSdk = 31

    viewBinding {
        isEnabled = true
    }



    buildFeatures {
        // Enables Jetpack Compose for this module
//        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }

    defaultConfig {
        applicationId = "dev.fstudio.weather"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")

    /*   Kotlinx Serialization (Better that Gson)   */
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")

    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    /*   Koin Modules   */
    implementation("io.insert-koin:koin-android:3.1.5")
    implementation("io.insert-koin:koin-ktor:3.1.5")
    implementation("io.insert-koin:koin-logger-slf4j:3.1.5")

    /*   Ktor Client   */
    implementation("io.ktor:ktor-client-core:1.6.7")
    implementation("io.ktor:ktor-client-android:1.6.7")
    implementation("io.ktor:ktor-client-serialization:1.6.7")

    // Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.0-alpha04")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.0-alpha04")

    // Settings Preference
    implementation("androidx.preference:preference-ktx:1.1.1")

    // Work Manager
    implementation("androidx.work:work-runtime:2.7.1")
    implementation("androidx.work:work-runtime-ktx:2.7.1")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")

    //Compose
    // Integration with activities
    implementation("androidx.activity:activity-compose:1.4.0")
    // Compose Material Design
    implementation("androidx.compose.material:material:1.1.1")
    // Animations
    implementation ("androidx.compose.animation:animation:1.1.1")
    // Tooling support (Previews, etc.)
    implementation ("androidx.compose.ui:ui-tooling:1.1.1")
    // Integration with ViewModels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    // UI Tests
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.1.1")

}