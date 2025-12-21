plugins {
    id(plugs.BuildPlugins.ANDROID_APPLICATION)
    id(plugs.BuildPlugins.KOTLIN_ANDROID)
    id(plugs.BuildPlugins.KOTLIN_COMPOSE)
    id(plugs.BuildPlugins.ANDROID)
    id(plugs.BuildPlugins.KSP)
}

android {
    namespace = build.BuildConfig.APPLICATION_ID
    compileSdk = build.BuildConfig.COMPILE_SDK


    defaultConfig {
        applicationId = build.BuildConfig.APPLICATION_ID
        minSdk = build.BuildConfig.MIN_SDK
        targetSdk = build.BuildConfig.TARGET_SDK
        versionCode = release.ReleaseConfig.VERSION_CODE
        versionName = release.ReleaseConfig.VERSION_NAME

        testInstrumentationRunner = test.TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        sigining.BuildSigning.Release(project).create(this)
        sigining.BuildSigning.ReleaseExternalQa(project).create(this)
        sigining.BuildSigning.Debug(project).create(this)
    }

    buildTypes {

        build.BuildCreator.Release(project).create(this).apply {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName(sigining.SigningTypes.RELEASE)
        }
        build.BuildCreator.Debug(project).create(this).apply {
            signingConfig = signingConfigs.getByName(sigining.SigningTypes.DEBUG)
        }
       build.BuildCreator.ReleaseExternalQa(project).create(this).apply {
            signingConfig = signingConfigs.getByName(sigining.SigningTypes.RELEASE_EXTERNAL_QA)
        }


    }
    flavorDimensions.add(build.BuildDimensions.APP)
    flavorDimensions.add(build.BuildDimensions.STORE)
    productFlavors {
        flavors.BuildFlavor.Google.create(this)
        flavors.BuildFlavor.Huawei.create(this)
        flavors.BuildFlavor.Driver.create(this)
        flavors.BuildFlavor.Client.create(this)
    }






    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig =true
    }
}

dependencies {
    implementation(deps.Dependencies.ANDROIDX_CORE)
    implementation(deps.Dependencies.ANDROIDX_LIFECYCLE_RUNTIME_KTX)
    implementation(deps.Dependencies.ANDROIDX_ACTIVITY_COMPOSE)
    implementation(deps.Dependencies.ANDROIDX_MATERIAL3)
    implementation(deps.Dependencies.ANDROIDX_UI_TOOLING_PREVIEW)
    implementation(deps.Dependencies.ANDROIDX_UI_GRAPHICS)
    implementation(deps.Dependencies.ANDROIDX_UI)

    testImplementation(test.TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(test.TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(test.TestDependencies.ANDROIDX_ESPRESSO_CORE)

    androidTestImplementation(test.TestDependencies.ANDROIDX_COMPOSE_UI_TEST)

    debugImplementation(deps.Dependencies.ANDROIDX_UI_TOOLING_PREVIEW)
    debugImplementation(test.TestDependencies.ANDROIDX_COMPOSE_UI_TEST_MANIFEST)
}