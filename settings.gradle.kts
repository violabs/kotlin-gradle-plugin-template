import java.util.*

rootProject.name = "template"

include("sandbox")

val publishingUrl: String by settings

// example of using the publication
if (publishingUrl.isNotBlank()) {
    val secretFileUsernameKey: String by settings
    val secretFileUsernamePassword: String by settings
    val envUsernameKey: String by settings
    val envPasswordKey: String by settings

    val props = Properties()
    file("secret.properties").inputStream().use {
        props.load(it)
    }

    val secretFileUsernameValue = props.getProperty(secretFileUsernameKey, "")
    val secretFilePasswordValue = props.getProperty(secretFileUsernamePassword, "")

    pluginManagement {
        repositories {
            maven {
                url = uri(publishingUrl)
                credentials {
                    username = secretFileUsernameValue ?: System.getenv(envUsernameKey) ?: ""
                    password = secretFilePasswordValue ?: System.getenv(envPasswordKey) ?: ""
                }
            }
            gradlePluginPortal()
            mavenCentral()
        }
    }
}