package deps

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import test.TestDependencies


fun DependencyHandler.retrofit(){
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)
    implementation(Dependencies.GSON)


}
fun DependencyHandler.okHttp(){
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_LOGGING_INTERCEPTOR)
}

fun DependencyHandler.room(){
    kspImplementation(Dependencies.ROOM_COMPILER)
    implementation(Dependencies.ROOM_RUNTIME)
    implementation(Dependencies.ROOM_KTX)
}
fun DependencyHandler.dataStore(){
    implementation(Dependencies.DATASTORE)
    implementation(Dependencies.KOTLIN_SERILAIZATIONS)
    implementation(Dependencies.KOTLIN_COLLECTIONS)
}

fun DependencyHandler.protoDataStore() {
    implementation(Dependencies.DATASTORE)
    implementation(Dependencies.PROTO_BUF_JAVA_LITE)
    implementation(Dependencies.PROTO_BUF_KOTLIN_LITE)
}

fun DependencyHandler.hilt(){
    kspImplementation(Dependencies.HILT_COMPILER)
    kspImplementation(Dependencies.HILT_AGP)
    implementation(Dependencies.HILT_ANDROID)
    implementation(Dependencies.HILT_COMPOSE_NAVIGATION)
    implementation(Dependencies.HILT_COMPOSE)
}


fun DependencyHandler.androidX(){
    implementation(Dependencies.ANDROIDX_CORE)
    implementation(Dependencies.ANDROIDX_LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.ANDROIDX_ACTIVITY_COMPOSE)
    implementation(Dependencies.ANDROIDX_MATERIAL3)
    implementation(Dependencies.ANDROIDX_UI_TOOLING_PREVIEW)
    implementation(Dependencies.ANDROIDX_UI_GRAPHICS)
    implementation(Dependencies.ANDROIDX_UI)
    implementation(Dependencies.WORK_RUNTIME)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.ANDROIDX_ACTIVITY)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_COMPILER)
    implementation(Dependencies.COMPOSE_RUNTIME)
}

fun DependencyHandler.loginModule() {
    moduleImplementation(project(":features:login"))
}

fun DependencyHandler.settingModule(){
    moduleImplementation(project(":features:setting"))
}

fun DependencyHandler.dataModule(){
    moduleImplementation(project(":core:data"))
}

fun DependencyHandler.domainModule(){
    moduleImplementation(project(":core:domain"))
}

fun DependencyHandler.presentationModule(){
    moduleImplementation(project(":core:presentation"))
}
fun DependencyHandler.dataStoreModule(){
    moduleImplementation(project(":core:datastore"))
}
fun DependencyHandler.protoDataStoreModule(){
    moduleImplementation(project(":core:protodatastore"))
}


fun DependencyHandler.testImplementation(){
    testImplementation(TestDependencies.ANDROIDX_JUNIT)
}

fun DependencyHandler.testAndroidImplementation(){
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(TestDependencies.ANDROIDX_ESPRESSO_CORE)
    androidTestImplementation(TestDependencies.ANDROIDX_COMPOSE_UI_TEST)
}
fun DependencyHandler.debugImplementation(){
    debugImplementation(Dependencies.ANDROIDX_UI_TOOLING_PREVIEW)
    debugImplementation(TestDependencies.ANDROIDX_COMPOSE_UI_TEST_MANIFEST)

}







