package sg.edu.nus.lapsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.lapsystem.model.Employee;
import sg.edu.nus.lapsystem.repository.EmployeeRepository;
import sg.edu.nus.lapsystem.repository.PositionRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private PositionRepository pr;
	@Autowired
	private EmployeeRepository er;
	
	
	public void SetAnnualLeaveDays(Employee employee) {
		int annualLeaveDaysLeft = pr.findByPositionName(employee.getPosition().getPositionName()).getAnnualLeaveDays();
		employee.setAnnualLeaveDaysLeft(annualLeaveDaysLeft); 
	}
	
	public Employee findById(int id) {
		return er.findById(id);
	}
	
}
