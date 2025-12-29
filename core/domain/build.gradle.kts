import deps.debugImplementation
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

    testImplementation()
    testAndroidImplementation()
    debugImplementation()
}
