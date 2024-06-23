plugins {
    id("org.springframework.boot") version "3.3.1" apply false
    id("io.spring.dependency-management") version "1.1.5" apply false
    kotlin("jvm") version "1.9.24"
    kotlin("kapt") version "1.9.21"
    kotlin("plugin.spring") version "1.9.24" apply false
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

allprojects {
    group = "com.cj.smartworker"
    version = "0.0.1-SNAPSHOT"
    repositories {
        mavenCentral()
        maven {
            url = uri("https://packages.confluent.io/maven")
        }
    }
}



subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testImplementation("org.testcontainers:junit-jupiter")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}


kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
