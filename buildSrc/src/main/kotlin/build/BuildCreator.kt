package build

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.LibraryBuildType
import extensions.buildConfigBooleanField
import extensions.buildConfigIntField
import extensions.buildConfigStringField
import extensions.getLocalProperty
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
sealed class BuildCreator(val buildType: String) {
    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType
    abstract fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType

    class Debug (private val project: Project) : BuildCreator(BuildTypes.DEBUG) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.getByName(buildType) {
                applicationIdSuffix = Build.Debug.applicationIdSuffix
                versionNameSuffix = Build.Debug.versionNameSuffix
                isMinifyEnabled = Build.Debug.isMinifyEnabled
                isDebuggable = Build.Debug.isDebuggable
                enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage

                buildConfigStringField(
                    BuildVariables.BASE_URL,
                    project.getLocalProperty("DEBUG_BASE_URL")
                )
                buildConfigIntField(
                    BuildVariables.DB_VERSION,
                    project.getLocalProperty("DEV_DB_VERSION")
                )
                buildConfigBooleanField(
                    BuildVariables.CAN_CLEAR_CACHE,
                    project.getLocalProperty("DEV_CLEAR_CACHE")
                )
                buildConfigStringField(
                    BuildVariables.MAP_KEY,
                    project.getLocalProperty("DEV_MAP_KEY")
                )

            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType {
            return namedDomainObjectContainer.getByName(buildType) {

                isMinifyEnabled = Build.Debug.isMinifyEnabled
                enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage
            }
        }

    }

    class Release(private val project: Project) : BuildCreator(BuildTypes.RELEASE) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.getByName(buildType) {
                isMinifyEnabled = Build.Release.isMinifyEnabled
                isDebuggable = Build.Release.isDebuggable
                enableUnitTestCoverage = Build.Release.enableUnitTestCoverage
                buildConfigStringField(
                    BuildVariables.BASE_URL,
                    project.getLocalProperty("RELEASE_BASE_URL")
                )
                buildConfigIntField(
                    BuildVariables.DB_VERSION,
                    project.getLocalProperty("PROD_DB_VERSION")
                )
                buildConfigBooleanField(
                    BuildVariables.CAN_CLEAR_CACHE,
                    project.getLocalProperty("PROD_CLEAR_CACHE")
                )
                buildConfigStringField(
                    BuildVariables.MAP_KEY,
                    project.getLocalProperty("PROD_MAP_KEY")
                )
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType {
            return namedDomainObjectContainer.getByName(buildType) {
                isMinifyEnabled = Build.Release.isMinifyEnabled
                enableUnitTestCoverage = Build.Release.enableUnitTestCoverage

            }
        }
    }

    class ReleaseExternalQa(private val project: Project) : BuildCreator(BuildTypes.RELEASE_EXTERNAL_QA) {

        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
       return  namedDomainObjectContainer.create(buildType) {
           applicationIdSuffix = Build.ReleaseExternalQa.applicationIdSuffix
           versionNameSuffix = Build.ReleaseExternalQa.versionNameSuffix
           isMinifyEnabled = Build.ReleaseExternalQa.isMinifyEnabled
           isDebuggable = Build.ReleaseExternalQa.isDebuggable
           enableUnitTestCoverage = Build.ReleaseExternalQa.enableUnitTestCoverage
           buildConfigStringField(
               BuildVariables.BASE_URL,
               project.getLocalProperty("RELEASE_BASE_URL")
           )
           buildConfigIntField(
               BuildVariables.DB_VERSION,
               project.getLocalProperty("PROD_DB_VERSION")
           )
           buildConfigBooleanField(
               BuildVariables.CAN_CLEAR_CACHE,
               project.getLocalProperty("PROD_CLEAR_CACHE")
           )
           buildConfigStringField(
               BuildVariables.MAP_KEY,
               project.getLocalProperty("PROD_MAP_KEY")
           )
       }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType {
            return  namedDomainObjectContainer.create(buildType) {

                isMinifyEnabled = Build.ReleaseExternalQa.isMinifyEnabled

                enableUnitTestCoverage = Build.ReleaseExternalQa.enableUnitTestCoverage

        }
    }
}
}