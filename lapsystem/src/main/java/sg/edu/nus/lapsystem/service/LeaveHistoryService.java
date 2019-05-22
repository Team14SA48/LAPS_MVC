package sg.edu.nus.lapsystem.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.lapsystem.model.Employee;
import sg.edu.nus.lapsystem.model.LeaveHistory;
import sg.edu.nus.lapsystem.repository.EmployeeRepository;
import sg.edu.nus.lapsystem.repository.LeaveHistoryRepository;

@Service
public class LeaveHistoryService {
	
	@Autowired
	private LeaveHistoryRepository lhr;
	@Autowired
	EmployeeRepository er;
	
	public List<LeaveHistory> listAllAppliedRequests() {
		return lhr.findByStatus("Applied");
	}
	
	public LeaveHistory findLeaveHistoryById(int id) {
		return lhr.findById(id);
	}
	
	public void save(LeaveHistory leaveHistory) {
		lhr.save(leaveHistory);
	}
	
	public List<LeaveHistory> listAppliedRequests(int ManagerId){
		Set<Integer> ids = new HashSet<Integer>();
		List<Employee> empList = er.findBySupervisor_Id(ManagerId);
		for(Employee e : empList) {
			ids.add(e.getId());
		}
		return lhr.findByStatusAndEmployee_IdIn("Applied",ids);
	}
	
	public List<LeaveHistory> findAll(){
		return lhr.findAllByOrderByIdDesc();
	}
}
