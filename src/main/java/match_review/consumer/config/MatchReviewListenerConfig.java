package match_review.consumer.config;
/* annotations */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.beans.factory.annotation.Value;
/* factory */
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
/* config */
import org.apache.kafka.clients.consumer.ConsumerConfig;
/* error handling */
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import match_review.consumer.bean.MatchReview;

/* serializer */
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
/* objects */
import java.util.HashMap;
import java.util.Map;


@EnableKafka
@Configuration
public class MatchReviewListenerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrap_servers;

    @Value("${spring.kafka.consumer.group-id}")
    private String group_id;

    @Bean
    public Map<String, Object> consumerConfiguration(){
        Map<String, Object> prop = new HashMap<>();
        /* server */
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        /* group id */
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, group_id);
        /* fetching wait and timeout */
        prop.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 500000); /* testing ~ former: 500k */
        prop.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG,300000); /* testing ~ former: 300k */
        /* fetching byte sizing + polling */
        prop.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1000000);
        prop.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 10000000);
        prop.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");
        /* deserialize */
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);       
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return prop;
    }

    @Bean
    public ConsumerFactory<String, MatchReview> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfiguration(), new StringDeserializer(), new JsonDeserializer<>(MatchReview.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MatchReview> matchReviewListenerContainer(){
        ConcurrentKafkaListenerContainerFactory<String, MatchReview> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true);
        factory.setCommonErrorHandler(eh());
        return factory;
    }

    @Bean
    public DefaultErrorHandler eh(){
        return new DefaultErrorHandler(new FixedBackOff(5, 2));
    }
}
