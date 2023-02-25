package match_review.consumer.components;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import match_review.consumer.bean.MatchReview;
import match_review.consumer.service.MatchReviewService;


@RequiredArgsConstructor
@Component
public class MatchReviewConsumer {
    
    private final String topicName = "match_review";

    @Autowired
    private MatchReviewService matchReviewService;
    
    @KafkaListener(topics = topicName, groupId = "consumer_api", containerFactory = "matchReviewListenerContainer" )
    public void consume(
        @Payload List<MatchReview> reviews
    ){
        matchReviewService.processNewMatchReviews(reviews);
    }
    
}
