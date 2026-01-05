package plugs

import build.BuildConfig
import build.BuildDimensions
import com.android.build.gradle.LibraryExtension
import flavors.BuildFlavor
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import test.TestBuildConfig


class SharedLibraryGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.addPluginConfigurations()
        project.addAndroidConfigurations()
        project.applyKotlinOptions()
    }

    private fun Project.addPluginConfigurations() {
        plugins.apply(BuildPlugins.KOTLIN_ANDROID)
        plugins.apply(BuildPlugins.KOTLIN_COMPOSE)
        plugins.apply(BuildPlugins.KSP)
        plugins.apply(BuildPlugins.KTLINT)
        plugins.apply(BuildPlugins.SERIALISATION)
    }

    private fun Project.addAndroidConfigurations() {
        extensions.getByType(LibraryExtension::class.java).apply {
            compileSdk = BuildConfig.COMPILE_SDK

            defaultConfig {
                minSdk = BuildConfig.MIN_SDK
                testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
            }

            signingConfigs {
                sigining.BuildSigning.Release(project).create(this)
                sigining.BuildSigning.ReleaseExternalQa(project).create(this)
                sigining.BuildSigning.Debug(project).create(this)
            }

            buildTypes {

                build.BuildCreator.Release(project).createLibrary(this).apply {
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                    signingConfig = signingConfigs.getByName(sigining.SigningTypes.RELEASE)
                }
                build.BuildCreator.Debug(project).createLibrary(this).apply {
                    signingConfig = signingConfigs.getByName(sigining.SigningTypes.DEBUG)
                }
                build.BuildCreator.ReleaseExternalQa(project).createLibrary(this).apply {
                    signingConfig =
                        signingConfigs.getByName(sigining.SigningTypes.RELEASE_EXTERNAL_QA)
                }


            }


            flavorDimensions.add(BuildDimensions.APP)
            flavorDimensions.add(BuildDimensions.STORE)

            productFlavors {
                BuildFlavor.Google.createLibrary(this)
                BuildFlavor.Huawei.createLibrary(this)
                BuildFlavor.Client.createLibrary(this)
                BuildFlavor.Driver.createLibrary(this)
            }

            buildFeatures {
                compose = true
                buildConfig = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }
    }

    private fun Project.applyKotlinOptions() {
        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_1_8)
            }
        }
    }
}