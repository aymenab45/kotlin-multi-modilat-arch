import deps.debugImplementation
import deps.kotlinx
import deps.testAndroidImplementation
import deps.testImplementation
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.example.domain"
}

dependencies {
    kotlinx()
    testImplementation()
    testAndroidImplementation()
    debugImplementation()
}
