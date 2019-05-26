package sg.edu.nus.lapsystem.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.lapsystem.model.Employee;
import sg.edu.nus.lapsystem.model.LeaveHistory;

public interface LeaveHistoryRepository extends JpaRepository<LeaveHistory, Integer> {
	List<LeaveHistory> findByStatus(String status);
	LeaveHistory findById(int id);
	// rewrite this like findByStatusAndEmployee_Supervisor
	List<LeaveHistory> findByStatusAndEmployee_IdIn(String status,Set<Integer> ids);
	List<LeaveHistory> findAllByOrderByIdDesc();
	List<LeaveHistory> findByEmployee_Id(int id);
	List<LeaveHistory> findByEmployee(Employee employee);
	List<LeaveHistory> findByEmployee_IdOrderByIdDesc(int id);
}
