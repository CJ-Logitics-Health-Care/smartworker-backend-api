package com.cj.smartworker.mongo.heart_rate.repository

import com.cj.smartworker.mongo.heart_rate.entity.HeartRateDocument
import com.cj.smartworker.mongo.heart_rate.value.HeartRateDataId
import org.springframework.data.mongodb.repository.MongoRepository

interface HeartRateMongoRepository: MongoRepository<HeartRateDocument, HeartRateDataId> {
}
