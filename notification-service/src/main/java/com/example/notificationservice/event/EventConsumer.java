package com.example.notificationservice.event;

import com.example.commonservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.RetriableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.*;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventConsumer {
    @Autowired
    private EmailService emailService;

    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 1000, multiplier = 2.0),
            autoCreateTopics = "true",
            dltStrategy = DltStrategy.FAIL_ON_ERROR,
            include = {RetriableException.class, RuntimeException.class}
    ) // Retry 4 times + 1 topic DLQ
    @KafkaListener(topics = "test", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message) {
        log.debug("Received message: " + message);
//        throw new RuntimeException("Test exception");
    }

    @DltHandler
    void processDltMessage(@Payload String message) {
        log.debug("Received message from DLT: " + message);
    }

    @KafkaListener(topics = "testEmail",containerFactory = "kafkaListenerContainerFactory")
    public void testEmail(String message){
        log.info("Received message: " +message);
        String template = "<div>\n" +
                "    <h1>Welcome, %s!</h1>\n" +
                "    <p>Thank you for joining us. We're excited to have you on board.</p>\n" +
                "    <p>Your username is: <strong>%s</strong></p>\n" +
                "</div>";
        String filledTemplate = String.format(template,"John",message);
        emailService.sendEmail(message,"Thanks for buy my course",filledTemplate,true,null);
    }
}
