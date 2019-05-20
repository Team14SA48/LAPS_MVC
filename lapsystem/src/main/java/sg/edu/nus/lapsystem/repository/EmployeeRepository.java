package sg.edu.nus.lapsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.lapsystem.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	
}
