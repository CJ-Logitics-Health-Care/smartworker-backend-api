package com.cj.smartworker.business.heart_rate

import com.cj.smartworker.kafka.model.HeartRateDto
import com.cj.smartworker.mongo.heart_rate.entity.HeartRateDocument
import com.cj.smartworker.mongo.heart_rate.repository.HeartRateMongoRepository
import com.cj.smartworker.mongo.heart_rate.value.HeartRateDataId
import com.cj.smartworker.util.HeartRateEncodingUtil
import org.springframework.stereotype.Service
import java.time.Instant
import com.cj.smartworker.util.toKstLocalDateTime

@Service
class SaveHeartRateService(
    private val heartRateMongoRepository: HeartRateMongoRepository,
) {
    fun save(heartRateDtos: List<HeartRateDto>) {
        val heartRateDocuments = heartRateDtos.map {
            HeartRateDocument(
                id = HeartRateDataId(
                    memberId = it.memberId,
                    timestamp = Instant.ofEpochSecond(it.timestamp).toKstLocalDateTime(),
                ),
                heartRate = HeartRateEncodingUtil.encrypt(it.heartRate.toString(), "12345678"),
            )
        }
        heartRateMongoRepository.saveAll(heartRateDocuments)
    }
}
