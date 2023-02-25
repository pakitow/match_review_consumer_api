package match_review.consumer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "match_review")
public class MatchReviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String uscis;

    @Column(name = "job_board")
    private int jobboard;

    @Column(name = "alternate_name")
    private String alternate;

    @Column(name = "uscis_id")
    private int uscisid;

    @Column
    private int country;

    @Column(columnDefinition = "TINYINT")
    private boolean confirmation;

    @Column(columnDefinition = "TINYINT")
    private boolean reviewed;

    public MatchReviewer(){}
    public MatchReviewer(int id, String uscis, String alternate, int uscisid, int country, boolean confirmation, boolean reviewed){
        setId(id);
        setUscis(uscis);
        setAlternate(alternate);
        setUscisid(uscisid);
        setCountry(country);
        setConfirmation(confirmation);
        setReviewed(reviewed);
    }

    public int getId(){
        return this.id;
    }
    public String getUscis(){
        return this.uscis;
    }
    public int getJobboard(){
        return this.jobboard;
    }
    public String getAlternate(){
        return this.alternate;
    }
    public int getUscisid(){
        return this.uscisid;
    }
    public int getCountry(){
        return this.country;
    }
    public boolean getConfirmation(){
        return this.confirmation;
    }
    public boolean getReviewed(){
        return this.reviewed;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setUscis(String uscis){
        this.uscis = uscis;
    }
    public void setJobboard(int jobboard){
        this.jobboard = jobboard;
    }
    public void setAlternate(String alternate){
        this.alternate = alternate;
    }
    public void setUscisid(int uscis_id){
        this.uscisid = uscis_id;
    }
    public void setCountry(int country){
        this.country = country;
    }
    public void setConfirmation(boolean confirmation){
        this.confirmation = confirmation;
    }
    public void setReviewed(boolean reviewed){
        this.reviewed = reviewed;
    }
}
