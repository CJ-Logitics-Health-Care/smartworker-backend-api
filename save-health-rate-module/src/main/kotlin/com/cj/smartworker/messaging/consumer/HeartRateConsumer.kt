package com.cj.smartworker.messaging.consumer

import com.cj.smartworker.annotation.MessageAdapter
import com.cj.smartworker.kafka.consumer.KafkaConsumer
import com.cj.smartworker.kafka.model.HeartRateDto
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload

@MessageAdapter
internal class HeartRateConsumer: KafkaConsumer<HeartRateDto> {
    override fun receive(
        @Payload messages: List<HeartRateDto>,
        @Header(KafkaHeaders.RECEIVED_KEY) keys: List<String>,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partitions: List<Int>,
        @Header(KafkaHeaders.OFFSET) offsets: List<Long>,
    ) {
        TODO("Not yet implemented")
    }
}
