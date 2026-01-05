import deps.debugImplementation
import deps.domainModule
import deps.hilt
import deps.okHttp
import deps.protoDataStoreModule
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
    namespace = "com.example.data"
}

dependencies {
    testImplementation()
    testAndroidImplementation()
    debugImplementation()
    hilt()
    room()
    okHttp()
    retrofit()
    protoDataStoreModule()
    domainModule()
}
