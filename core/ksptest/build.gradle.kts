import deps.debugImplementation
import deps.hilt
import deps.protoDataStoreModule
import deps.testAndroidImplementation
import deps.testImplementation
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.example.ksptest"
}

dependencies {
    testImplementation()
    testAndroidImplementation()
    debugImplementation()
    hilt()
    protoDataStoreModule()
}
