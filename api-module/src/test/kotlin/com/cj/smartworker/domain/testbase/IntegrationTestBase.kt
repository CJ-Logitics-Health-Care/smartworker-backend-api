package com.cj.smartworker.domain.testbase

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.MongoDBContainer
import java.time.Duration

@SpringBootTest
@ContextConfiguration(initializers = [IntegrationTestBase.IntegrationTestInitializer::class])
class IntegrationTestBase {

    companion object {
        private val MONGO_CONTAINER = MongoDBContainer("mongo:latest")
            .apply { withStartupTimeout(Duration.ofSeconds(300)) }
            .apply { withReuse(true) }
    }

    class IntegrationTestInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            MONGO_CONTAINER.start()

            val properties: Map<String, String> = hashMapOf(
                "spring.datasource.driver-class-name" to "org.testcontainers.jdbc.ContainerDatabaseDriver",
                "spring.datasource.url" to "jdbc:tc:mysql:8.0:///test?TC_REUSABLE=true",
                "spring.data.mongodb.uri" to MONGO_CONTAINER.replicaSetUrl,
                "spring.data.mongodb.database" to "test",
                "spring.data.mongodb.auto-index-creation" to true.toString(),
            )
        }

    }
}
