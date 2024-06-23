plugins {
    kotlin("plugin.jpa") version "1.9.20"
    kotlin("kapt") version "1.9.21"
    idea
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    runtimeOnly("com.mysql:mysql-connector-j")
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter")
    // Test
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.testcontainers:mysql")
}
tasks.test {
    useJUnitPlatform()
}

// ✅ QClass를 Intellij가 사용할 수 있도록 경로에 추가합니다
var querydslDir = "$buildDir/generated/source/kapt/main"
idea {
    module {
        val kaptMain = file(querydslDir)
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}
