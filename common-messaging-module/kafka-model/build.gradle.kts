dependencies {
    api("io.confluent:kafka-avro-serializer:${System.getProperty("avroVersion")}")
    api("io.confluent:kafka-avro-serializer:${System.getProperty("serializerVersion")}")
    api("org.springframework.kafka:spring-kafka")
}
