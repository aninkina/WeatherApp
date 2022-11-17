// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {



    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath ("io.insert-koin:koin-gradle-plugin:2.2.3")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.0-alpha04")
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}