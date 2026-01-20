import deps.androidX
import deps.debugImplementation
import deps.hilt
import deps.testAndroidImplementation
import deps.testImplementation
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.example.navigator"
}

dependencies {
    androidX()
    hilt()
    testImplementation()
    testAndroidImplementation()
    debugImplementation()
}
