package com.example.userManagement.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
public class KafkaProducerLoggerCallback<T> implements ListenableFutureCallback<SendResult<String, T>> {

    private final T message;

    public KafkaProducerLoggerCallback(T message) {
        this.message = message;
    }

    @Override
    public void onSuccess(SendResult<String, T> result) {
        log.info("Sent message=[" + this.message.toString() +
                "] with offset=[" + result.getRecordMetadata().offset() + "]" +
                " to topic=[" + result.getRecordMetadata().topic() + "]");
    }

    @Override
    public void onFailure(Throwable ex) {
        log.error("Unable to send message=["
                + this.message.toString() + "] due to : " + ex.getMessage());
    }
}
