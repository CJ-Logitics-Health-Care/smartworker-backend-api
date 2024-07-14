package com.cj.smartworker.messaging.publisher

import com.cj.smartworker.annotation.MessageAdapter
import com.cj.smartworker.kafka.model.HeartRateDto
import com.cj.smartworker.kafka.producer.service.KafkaProducer
import com.cj.smartworker.messaging.configuration.KafkaTopicConfigData


@MessageAdapter
internal class HeartRateMassagePublisher(
    private val kafkaProducer: KafkaProducer<String, HeartRateDto>,
    private val kafkaTopicConfigData: KafkaTopicConfigData,
) {
    fun publishHearthRate() {
        println("Publishing hearth rate")
    }
}
