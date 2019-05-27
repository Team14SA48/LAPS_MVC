package sg.edu.nus.lapsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.lapsystem.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findBySupervisor_Id(int SupervisorId);
	Employee findById(int id);
	public Employee findByName(String Name);
}
