package sg.edu.nus.lapsystem.repository;

import java.util.ArrayList;

import sg.edu.nus.lapsystem.model.Employee;
//import javax.transaction.Transactional;
import sg.edu.nus.lapsystem.model.LeaveHistory;

public interface LeaveServiceIF {
//	Employee authenticate(String empName, String pwd);
//	
	ArrayList<LeaveHistory> findAllLeaves();

	LeaveHistory findLeave(int leaveId);

	LeaveHistory changeLeave(LeaveHistory leave);

	void removeLeave(LeaveHistory leave);

}
