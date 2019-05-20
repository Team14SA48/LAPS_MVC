package sg.edu.nus.lapsystem.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class LeaveCategory {
	@Id
	@Column(length = 30)
	private String leaveCategory;
	@OneToMany(mappedBy = "leaveCategory")
	private Set<LeaveHistory> leaveHistory = new HashSet<LeaveHistory>();

	// getter setter
	public String getLeaveCategory() {
		return leaveCategory;
	}

	public void setLeave(String leaveCategory) {
		this.leaveCategory = leaveCategory;
	}

	public Set<LeaveHistory> getLeaveHistory() {
		return leaveHistory;
	}

	public void setLeaveHistory(Set<LeaveHistory> leaveHistory) {
		this.leaveHistory = leaveHistory;
	}

	// constructor
	public LeaveCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveCategory(String leaveCategory) {
		super();
		this.leaveCategory = leaveCategory;
	}

	@Override
	public String toString() {
		return "LeaveCategory [leaveCategory=" + leaveCategory + ", NumberOfleaveHistory=" + leaveHistory.size() + "]";
	}

}
