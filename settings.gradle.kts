rootProject.name = "template"

include("sandbox")

val publishingUrl: String by settings

// example of using the publication
if (publishingUrl.isNotBlank()) {
    pluginManagement {
        repositories {
            gradlePluginPortal()
            mavenCentral()
            maven {
                url = uri(publishingUrl)
                credentials {
                    val secretFileUsernameKey: String by settings
                    val secretFilePasswordKey: String by settings
                    val envUsernameKey: String by settings
                    val envPasswordKey: String by settings

                    val props = mutableMapOf<String, String>()

                    file("secret.properties").readText().lines().forEach {
                        val (key, value) = it.split("=")
                        props[key] = value
                    }

                    val secretFileUsernameValue = props[secretFileUsernameKey]
                    val secretFilePasswordValue = props[secretFilePasswordKey]

                    username = secretFileUsernameValue ?: System.getenv(envUsernameKey) ?: ""
                    password = secretFilePasswordValue ?: System.getenv(envPasswordKey) ?: ""
                }
            }
        }

        plugins {
//            id("plugin name") version "1.0.0"
        }

        resolutionStrategy {
            logger.log(LogLevel.LIFECYCLE, "Starting plugin request")
            eachPlugin {
                logger.log(LogLevel.LIFECYCLE, "Plugin requested: ${requested.id}")
                logger.log(LogLevel.LIFECYCLE, "Plugin requested namespace: ${requested.id.namespace}")
                if (requested.id.toString() == "plugin name") {
                    logger.log(LogLevel.LIFECYCLE, "Using plugin: ${requested.id}")
                    useModule("company:artifact:1.0.0")
                }
            }
        }
    }
}