package match_review.consumer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import match_review.consumer.bean.MatchRecord;
import match_review.consumer.repository.H1BRecordRepository;
import match_review.consumer.repository.MatchReviewRepository;



@Service
public class CachedMethods {
    @Autowired
    private H1BRecordRepository h1bRecordRepository;

    @Autowired
    private MatchReviewRepository matchReviewRepository;

    public List<MatchRecord> getMatchRecords(String literal){
        return h1bRecordRepository.getMatchRecords(literal);
    }

    @Cacheable(value = "can_insert_match_review", key = "{#uscis_name, #alternate_name, 'exist'}")
    public boolean canInsertNewMatchReview(String uscis_name, String alternate_name){
        if(matchReviewRepository.existsMatchReviewerByUscis(uscis_name) && matchReviewRepository.existsMatchReviewerByAlternate(alternate_name)){
            return false;
        }
        return true;
    }

    public void insertNewMatchReview(String uscis_name, int job_board, String alternate_name, int uscis_id, int country){
        matchReviewRepository.insertNewRecord(uscis_name, job_board, alternate_name, uscis_id, country);
    }

}
