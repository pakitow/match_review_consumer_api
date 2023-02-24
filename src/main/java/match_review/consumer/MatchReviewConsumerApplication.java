package match_review.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class MatchReviewConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MatchReviewConsumerApplication.class, args);
    }
}