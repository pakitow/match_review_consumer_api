package match_review.consumer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import match_review.consumer.bean.MatchRecord;
import match_review.consumer.bean.MatchReview;

@Service
public class MatchReviewService {
    
    @Autowired
    private CachedMethods cachedMethods;

    public void processNewMatchReviews(List<MatchReview> reviews){
        for(int i=0; i<reviews.size(); i++){
            MatchReview review = reviews.get(i);
            insertNewMatchReviews(review.name, review.literal);  
        }
    }

    @Async
    public void insertNewMatchReviews(String alternate_name, String literal_match){
        List<MatchRecord> records = cachedMethods.getMatchRecords(literal_match);
        for(int i=0; i<records.size(); i++){
            MatchRecord record = records.get(i);
            insertNewMatchReview(record.getUscisname(), 1, alternate_name, record.getUniqueid(), 0);
        }
    }

    @Async
    public void insertNewMatchReview(String uscis_name, int job_board, String alternate_name, int uscis_id, int country){
        if(proceed(uscis_name, alternate_name)){
            cachedMethods.insertNewMatchReview(uscis_name, job_board, alternate_name, uscis_id, country);
        }
    }
    
    public boolean proceed(String uscis_name, String alternate_name){
        return cachedMethods.canInsertNewMatchReview(uscis_name, alternate_name);
    }

}