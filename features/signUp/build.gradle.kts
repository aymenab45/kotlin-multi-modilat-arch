import deps.androidX
import deps.dataModule
import deps.debugImplementation
import deps.domainModule
import deps.hilt
import deps.presentationModule
import deps.retrofit
import deps.room
import deps.testAndroidImplementation
import deps.testImplementation
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id(plugs.BuildPlugins.HILT) version deps.DependenciesVersions.HILT
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.example.signup"

    buildFeatures {
        compose = true
    }
}

dependencies {
    presentationModule()
    dataModule()
    domainModule()
    androidX()
    testImplementation()
    testAndroidImplementation()
    debugImplementation()
    hilt()
    room()
    retrofit()
}
