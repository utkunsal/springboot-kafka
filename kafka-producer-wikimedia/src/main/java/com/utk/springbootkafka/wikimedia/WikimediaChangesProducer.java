package com.utk.springbootkafka.wikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {

        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topicName);

        try (EventSource eventSource = new EventSource.Builder(
                eventHandler,
                URI.create("https://stream.wikimedia.org/v2/stream/recentchange"))
                .build()) {

            eventSource.start();
            TimeUnit.MINUTES.sleep(10);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
