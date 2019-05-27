package sg.edu.nus.lapsystem.model;

import java.time.LocalDate;

public class datainput {
	LocalDate startDate;
	LocalDate endDate;
	String leaveType;
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public datainput() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
