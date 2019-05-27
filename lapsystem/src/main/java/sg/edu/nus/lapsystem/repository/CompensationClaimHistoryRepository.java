package sg.edu.nus.lapsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.lapsystem.model.Employee;
import sg.edu.nus.lapsystem.model.CompensationClaimHistory;

public interface CompensationClaimHistoryRepository extends JpaRepository<CompensationClaimHistory, Integer>{
//	List<CompensationClaimHistory> findAllByEmployee(Employee employee);
	CompensationClaimHistory findById(int compensationId);
	List<CompensationClaimHistory> findAllByEmployee(Employee employee);

}
