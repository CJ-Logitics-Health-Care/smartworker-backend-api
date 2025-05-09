import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    api("org.springframework.kafka:spring-kafka")
    api(project(":common-messaging-module:kafka-model"))
}

tasks.named<BootJar>("bootJar") {
    enabled = false
}

tasks.named<Jar>("jar") {
    enabled = true
}
