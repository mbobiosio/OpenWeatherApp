// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    var kotlin_version: String by extra
    kotlin_version = "1.5.31"
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.Gradle.BUILD_TOOLS}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        //classpath(kotlinModule("gradle-plugin", kotlin_version))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean",  Delete::class) {
    delete(rootProject.buildDir)
}