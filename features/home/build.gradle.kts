import deps.androidX
import deps.debugImplementation
import deps.domainModule
import deps.navigatorModule
import deps.presentationModule
import deps.retrofit
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
    navigatorModule()
    domainModule()
    androidX()
    testImplementation()
    testAndroidImplementation()
    debugImplementation()
    retrofit()
}
