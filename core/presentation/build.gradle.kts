import deps.androidX
import deps.debugImplementation
import deps.domainModule
import deps.testAndroidImplementation
import deps.testImplementation
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.example.presentation"
}

dependencies {
    androidX()
    domainModule()
    testImplementation()
    testAndroidImplementation()
    debugImplementation()
}
