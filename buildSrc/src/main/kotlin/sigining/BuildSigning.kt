package sigining

import com.android.build.api.dsl.ApkSigningConfig
import extensions.getLocalProperty
import org.gradle.api.NamedDomainObjectContainer
import java.io.File
import org.gradle.api.Project
sealed class BuildSigning(val name: String) {

    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>)


    class Release(private val project: Project) : BuildSigning(SigningTypes.RELEASE) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>) {
            namedDomainObjectContainer.create(name) {
                storeFile = File(project.getLocalProperty("RELEASE_STORE_FILE"))
                storePassword = project.getLocalProperty("RELEASE_STORE_PASSWORD")
                keyAlias = project.getLocalProperty("RELEASE_KEY_ALIAS")
                keyPassword =project.getLocalProperty("RELEASE_KEY_PASSWORD")
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }

    class ReleaseExternalQa(private  val project: Project) : BuildSigning(SigningTypes.RELEASE_EXTERNAL_QA) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>) {
            namedDomainObjectContainer.create(name) {
                storeFile = File(project.getLocalProperty("QA_STORE_FILE"))
                storePassword = project.getLocalProperty("QA_STORE_PASSWORD")
                keyAlias = project.getLocalProperty("QA_KEY_ALIAS")
                keyPassword =project.getLocalProperty("QA_KEY_PASSWORD")
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }

    class Debug(private val project: Project) : BuildSigning(SigningTypes.DEBUG) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>) {
            namedDomainObjectContainer.getByName(name) {
                storeFile = File(project.rootProject.rootDir,"debug.keystore")
                storePassword = "android"
                keyAlias = "androiddebugkey"
                keyPassword = "android"
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }

}