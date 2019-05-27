package sg.edu.nus.lapsystem.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class LeaveHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Employee employee;
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate leaveStartDate;
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate leaveEndDate;
	@Column(nullable = false)
	private int leaveDays;
	@Column(nullable = false)
	private LocalDate submitDate;
	@ManyToOne
	private LeaveCategory leaveCategory;
	private String additionalReasons;
	private String workDissemination;
	private String contractDetail;
	@Column(nullable = false, length = 30)
	private String status;
	private String rejectReason;

	// getter setter

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(LocalDate leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public LocalDate getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(LocalDate leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	public int getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(int leaveDays) {
		this.leaveDays = leaveDays;
	}

	public LocalDate getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(LocalDate submitDate) {
		this.submitDate = submitDate;
	}

	public LeaveCategory getLeaveCategory() {
		return leaveCategory;
	}

	public void setLeaveCategory(LeaveCategory leaveCategory) {
		this.leaveCategory = leaveCategory;
	}

	public String getAdditionalReasons() {
		return additionalReasons;
	}

	public void setAdditionalReasons(String additionalReasons) {
		this.additionalReasons = additionalReasons;
	}

	public String getWorkDissemination() {
		return workDissemination;
	}

	public void setWorkDissemination(String workDissemination) {
		this.workDissemination = workDissemination;
	}

	public String getContractDetail() {
		return contractDetail;
	}

	public void setContractDetail(String contractDetail) {
		this.contractDetail = contractDetail;
	}

	public String getStatus() {
		return status;
	}

	// status transition
	public void setStatus(String status) {
		this.status = status;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	// constructor
	public LeaveHistory() {
		super();
	}

	public LeaveHistory(Employee employee, LocalDate leaveStartDate, LocalDate leaveEndDate,
			LeaveCategory leaveCategory, String additionalReasons,String status) {
		this(employee, leaveStartDate, leaveEndDate, LocalDate.now(), leaveCategory, additionalReasons,status);
	}

	public LeaveHistory(Employee employee, LocalDate leaveStartDate, LocalDate leaveEndDate, LocalDate submitDate,
			LeaveCategory leaveCategory, String additionalReasons, String status) {
		this.employee = employee;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.submitDate = submitDate;
		this.leaveCategory = leaveCategory;
		this.additionalReasons = additionalReasons;
		this.status = status;
	}

	@Override
	public String toString() {
		return "LeaveHistory [id=" + id + ", employee=" + employee.getName() + ", leaveStartDate=" + leaveStartDate
				+ ", leaveEndDate=" + leaveEndDate + ", leaveDays=" + leaveDays + ", submitDate=" + submitDate
				+ ", leaveCategory=" + leaveCategory.getLeaveCategory() + ", additionalReasons=" + additionalReasons
				+ ", workDissemination=" + workDissemination + ", contractDetail=" + contractDetail + ", status="
				+ status + ", rejectReason=" + rejectReason + "]";
	}

}
