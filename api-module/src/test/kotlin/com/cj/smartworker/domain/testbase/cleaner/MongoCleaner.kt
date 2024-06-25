package com.cj.smartworker.domain.testbase.cleaner

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

@Component
class MongoCleaner(
    private val mongoTemplate: MongoTemplate,
) {
    fun cleanAll() {
        mongoTemplate.collectionNames.forEach {
            mongoTemplate.dropCollection(it)
        }
    }
}
