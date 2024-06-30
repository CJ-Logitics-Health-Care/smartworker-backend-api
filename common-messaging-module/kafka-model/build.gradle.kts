val avroVersion: String by project
val serializerVersion: String by project

dependencies {
    api("org.apache.avro:avro:$avroVersion")
    api("io.confluent:kafka-avro-serializer:$serializerVersion")
    api("org.springframework.kafka:spring-kafka")
}
