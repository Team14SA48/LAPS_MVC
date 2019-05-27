package sg.edu.nus.lapsystem.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import sg.edu.nus.lapsystem.model.Employee;

@Entity
public class CompensationClaimHistory {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Date overTimeWorkDateFrom;
	private Date overTimeWorkDateTo;
	public Date getOverTimeWorkDateTo() {
		return overTimeWorkDateTo;
	}
	public void setOverTimeWorkDateTo(Date overTimeWorkDateTo) {
		this.overTimeWorkDateTo = overTimeWorkDateTo;
	}
	@ManyToOne
	private Employee employee;
	private double compensationDuration;
	private String status;
	private Date applyDate;
	private String overTimeworkReason;
	
	//getter setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getOverTimeWorkDateFrom() {
		return overTimeWorkDateFrom;
	}
	public void setOverTimeWorkDateFrom(Date overTimeWorkDateFrom) {
		this.overTimeWorkDateFrom = overTimeWorkDateFrom;
	}
	
	public double getCompensationDuration() {
		return compensationDuration;
	}
	public void setCompensationDuration(double compensationDuration) {
		this.compensationDuration = compensationDuration;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getOverTimeworkReason() {
		return overTimeworkReason;
	}
	public void setOverTimeworkReason(String overTimeworkReason) {
		this.overTimeworkReason = overTimeworkReason;
	}
	
	//constructor
	public CompensationClaimHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "CompensationClaimHistory [id=" + id + ", overTimeWorkDate=" + overTimeWorkDateFrom + ", employeeId="
				+ employee.getId() + ", compensationDuration=" + compensationDuration + ", status=" + status + ", applyDate="
				+ applyDate + ", overTimeworkReason=" + overTimeworkReason + "]";
	}
}
