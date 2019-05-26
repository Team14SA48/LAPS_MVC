package sg.edu.nus.lapsystem.Access;

import sg.edu.nus.lapsystem.model.Employee;

public class UserAccess {

	private boolean applyLeave = false;
	private boolean approveLeave = false;

	public UserAccess() {
		super();
	}

	public UserAccess(Employee employee) {
		String role = employee.getRole();
		switch (role) {
		case "employee":
			applyLeave = true;
			break;
		case "manager":
			applyLeave = true;
			approveLeave = true;
			break;
		case "Administrator":
			applyLeave = true;
			approveLeave = true;
		}
	}

	public boolean isApplyLeave() {
		return applyLeave;
	}

	public boolean isApproveLeave() {
		return approveLeave;
	}

}
