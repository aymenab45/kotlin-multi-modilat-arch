
plugins {
    `kotlin-dsl`
}

repositories{
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies{

    api(kotlin("gradle-plugin:2.0.21"))
    implementation("com.android.tools.build:gradle:8.13.1")
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.0.21")
    implementation("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:2.0.20-1.0.25")


}