package match_review.consumer.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;


public class MatchReview {
    
    @JsonProperty("name") public String name;

    @JsonProperty("literal") public String literal;

    @JsonCreator
    public MatchReview(
        @JsonProperty("name") String name,
        @JsonProperty("literal") String literal
    ){
        super();
        this.name = name;
        this.literal = literal;
    }
}
