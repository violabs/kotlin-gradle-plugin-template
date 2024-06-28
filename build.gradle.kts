import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "2.0.0-Beta5"
    kotlin("plugin.spring") version "2.0.0-Beta5"
}

group = "io.violabs"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("io.github.microutils:kotlin-logging:4.0.0-beta-2")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")


//	implementation("org.springframework.boot:spring-boot-starter-web")
//	implementation("org.springdoc:springdoc-openapi-ui:1.8.0")
//	implementation("org.springdoc:springdoc-openapi-webmvc-core:1.8.0")

//	implementation("org.springframework.boot:spring-boot-starter-webflux")
//	implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.3.0")
//  implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

//	testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.violabs.geordi:unit-sim:1.0.5")
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
