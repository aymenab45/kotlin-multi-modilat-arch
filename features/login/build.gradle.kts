import deps.androidX
import deps.dataModule
import deps.debugImplementation
import deps.domainModule
import deps.hilt
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
    namespace = "com.example.login"
}

dependencies {
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
