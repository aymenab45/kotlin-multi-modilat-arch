import deps.Dependencies.PROTO_BUF_ARTIFACT
import deps.dataStore
import deps.debugImplementation
import deps.hilt
import deps.protoDataStore
import deps.testAndroidImplementation
import deps.testImplementation
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id(plugs.BuildPlugins.GOOGLE_PROTOBUF) version "0.9.6"
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.example.protodatastore"
}
protobuf {
    protoc {
        artifact = PROTO_BUF_ARTIFACT
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("kotlin").apply {
                    option("lite")
                }
            }
            task.plugins {
                create("java").apply {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    protoDataStore()
    hilt()
    dataStore()
    testImplementation()
    testAndroidImplementation()
    debugImplementation()
}
