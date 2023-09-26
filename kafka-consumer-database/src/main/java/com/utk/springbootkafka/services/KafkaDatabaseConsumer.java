package com.utk.springbootkafka.services;

import com.utk.springbootkafka.entities.WikimediaData;
import com.utk.springbootkafka.repositories.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
    private WikimediaDataRepository wikimediaDataRepository;

    public KafkaDatabaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(String eventMessage){
        LOGGER.info(String.format("Event message received: %s", eventMessage));

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setEventData(eventMessage);
        wikimediaDataRepository.save(wikimediaData);
    }

}
