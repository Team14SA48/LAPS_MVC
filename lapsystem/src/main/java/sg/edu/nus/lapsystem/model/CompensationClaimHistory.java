package sg.edu.nus.lapsystem.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompensationClaimHistory {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Date overTimeWorkDate;
	private int employeeId;
	private int compensationDuration;
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
	public Date getOverTimeWorkDate() {
		return overTimeWorkDate;
	}
	public void setOverTimeWorkDate(Date overTimeWorkDate) {
		this.overTimeWorkDate = overTimeWorkDate;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getCompensationDuration() {
		return compensationDuration;
	}
	public void setCompensationDuration(int compensationDuration) {
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
		return "CompensationClaimHistory [id=" + id + ", overTimeWorkDate=" + overTimeWorkDate + ", employeeId="
				+ employeeId + ", compensationDuration=" + compensationDuration + ", status=" + status + ", applyDate="
				+ applyDate + ", overTimeworkReason=" + overTimeworkReason + "]";
	}
	
	
}
