package match_review.consumer.bean;

public class MatchRecord {
    public String uscisname;
    public int uniqueid;

    public MatchRecord(String uscisname, int uniqueid){
        setUscisname(uscisname);
        setUniqueid(uniqueid);
    }

    public void setUscisname(String uscisname){
        this.uscisname = uscisname;
    }
    public void setUniqueid(int uniqueid){
        this.uniqueid = uniqueid;
    }
    public String getUscisname(){
        return this.uscisname;
    }
    public int getUniqueid(){
        return this.uniqueid;
    }

}
