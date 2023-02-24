package match_review.consumer.components;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import match_review.consumer.bean.MatchReview;


@RequiredArgsConstructor
@Component
@Slf4j
public class MatchReviewConsumer {
    
    private final String topicName = "match_review";
    
    @KafkaListener(topics = topicName, groupId = "consumer_api", containerFactory = "matchReviewListenerContainer" )
    public void consume(
        /* @Payload List<ConsumerRecord<String, MatchReview>> reviews */
        @Payload List<MatchReview> reviews
    ){
        log.info("------------------------------");
        log.info("Starting batch processing");
        for(int i = 0; i < reviews.size(); i++){
            log.info("Topic: {}", topicName);
            log.info("MatchReview [name]: {}", reviews.get(i).name);
            log.info("MatchReview [literal]: {}", reviews.get(i).literal);
        }
        log.info("batch was fully processed");
    }
    
}
