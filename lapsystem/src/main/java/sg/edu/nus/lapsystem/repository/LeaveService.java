package sg.edu.nus.lapsystem.repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.lapsystem.model.Employee;
import sg.edu.nus.lapsystem.model.LeaveHistory;

@Service
public class LeaveService implements LeaveServiceIF {
	@Autowired
	private EmployeeRepository eRepo;
	
	@Resource
	private LeaveHistoryRepository lhr;
	
//	@Transactional
//	public Employee authenticate(String name,String pwd)
//	{
//		List<Employee> eList=eRepo.findByempNameAndPwd(name, pwd);
//		return eList.get(0);
//	}
	
	@Override
	@Transactional
	public ArrayList<LeaveHistory> findAllLeaves() {
		ArrayList<LeaveHistory> history = (ArrayList<LeaveHistory>) lhr.findAll();
		return history;
	}
	
	@Override
	@Transactional
	public LeaveHistory findLeave(int leaveId) {
		return lhr.findById(leaveId);

	}

	@Override
	@Transactional
	public LeaveHistory changeLeave(LeaveHistory leave) {
		return lhr.saveAndFlush(leave);
	}
	
	@Override
	@Transactional
	public void removeLeave(LeaveHistory leave) {
		lhr.delete(leave);
	}


}
