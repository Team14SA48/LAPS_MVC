package sg.edu.nus.lapsystem.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LeaveHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Employee employee;
	@Column(nullable = false)
	private LocalDate leaveStartDate;
	@Column(nullable = false)
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
		employee.getLeaveHistories().add(this);
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
		leaveCategory.getLeaveHistory().add(this);
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
		// TODO Auto-generated constructor stub
	}

	public LeaveHistory(Employee employee, LocalDate leaveStartDate, LocalDate leaveEndDate,
			LeaveCategory leaveCategory, String additionalReasons, String status) {
		super();
		this.employee = employee;
		employee.getLeaveHistories().add(this);
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		leaveDays = (int) ChronoUnit.DAYS.between(leaveStartDate,leaveEndDate)+1;
		this.submitDate = LocalDate.now();
		this.leaveCategory = leaveCategory;
		leaveCategory.getLeaveHistory().add(this);
		this.additionalReasons = additionalReasons;
		this.status = status;
		// have to maintain here
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
