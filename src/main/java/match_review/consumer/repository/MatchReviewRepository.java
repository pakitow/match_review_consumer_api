package match_review.consumer.repository;

import match_review.consumer.model.MatchReviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface MatchReviewRepository extends JpaRepository<MatchReviewer, Integer>{
    
    boolean existsMatchReviewerByUscis(String name);

    boolean existsMatchReviewerByAlternate(String name);

    @Procedure(procedureName = "INSERT_MATCH_REVIEW_RECORD")
    void insertNewRecord(String uscis_name, int job_board, String alternate_name, int uscis_id, int country);
    
    
}
