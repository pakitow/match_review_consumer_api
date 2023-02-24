package match_review.consumer.components;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Component
@Slf4j
public class MatchReviewConsumer {
    
    private final String topicName = "match_review";
    
    
    @KafkaListener(topics = topicName, groupId = "rest_api")
    public void consume(ConsumerRecord<String, String> review){
        log.info("Topic: {}", topicName);
        log.info("Headers: {}", review.key());
        log.info("Partition: {}", review.partition());
        log.info("Order: {}", review.value());
    }
    
}
