rootProject.name = "smartworker-backend-api"

include(":api-module")
include(":save-health-rate-module")
include(":alarm-module")
include(":document-db-module")
include(":common-messaging-module")
include(":common-messaging-module:kafka-config-data")
include(":common-messaging-module:kafka-consumer")
include(":common-messaging-module:kafka-model")
include(":common-messaging-module:kafka-producer")

pluginManagement {
    val kotlinVersion : String by settings
    val springBootVersion : String by settings
    val springDependencyManagementVersion : String by settings
    val jvmVersion : String by settings
    val kaptVersion: String by settings
    val pluginSpring: String by settings
    val pluginJpa: String by settings

    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven {
            url = uri("https://packages.confluent.io/maven")
        }
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "jvm" -> useVersion(jvmVersion)
                "kapt" -> useVersion(kaptVersion)
                "plugin.spring" -> useVersion(pluginSpring)
                "plugin.jpa" -> useVersion(pluginJpa)
            }
        }
    }
}
