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
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.example.home"

    buildFeatures {
        compose = true
    }
}

dependencies {
    presentationModule()

    domainModule()
    androidX()
    testImplementation()
    testAndroidImplementation()
    debugImplementation()
    retrofit()

}
