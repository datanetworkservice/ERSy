package nl.knaw.dans.ersy.orm.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "pid_relevancy")
public class PidRelevancySimple {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private int id;
     
    @Column(name = "pid")
    private String pid;
    
    @Column(name = "pid_rel")
    private String pidRel;
    
    @Column(name="distance")
    private double distance;
    
    @Column(name="rating")
    private int rating;
     
    
    public PidRelevancySimple() {
    }
    
    public PidRelevancySimple(String pid, String pidRel, double distance) {
        this.pid = pid;
        this.pidRel = pidRel;
        this.distance = distance;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPidRel() {
		return pidRel;
	}

	public void setPidRel(String pidRel) {
		this.pidRel = pidRel;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}