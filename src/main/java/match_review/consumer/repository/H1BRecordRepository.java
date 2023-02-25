package match_review.consumer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import match_review.consumer.bean.MatchRecord;
import match_review.consumer.model.H1BRecord;

public interface H1BRecordRepository extends JpaRepository<H1BRecord, Integer>{

   @Query(
    "SELECT NEW match_review.consumer.bean.MatchRecord(h.company, h.code) FROM H1BRecord h WHERE h.company LIKE CONCAT(?1, ' %') OR h.company=?1"
   )
   List<MatchRecord> getMatchRecords(String name);

}

