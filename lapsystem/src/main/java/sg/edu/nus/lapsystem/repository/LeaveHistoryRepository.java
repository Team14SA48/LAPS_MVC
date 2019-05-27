package sg.edu.nus.lapsystem.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
	
	@Query("select p from LeaveHistory p where p.leaveStartDate between ?1 and ?2 or p.leaveEndDate between ?1 and ?2")
	List<LeaveHistory> withDateQueryAll(LocalDate startDate,LocalDate endDate);
	@Query("select p from LeaveHistory p where (p.leaveCategory.leaveCategory=?3) and (p.leaveStartDate between ?1 and ?2 or p.leaveEndDate between ?1 and ?2)")
	List<LeaveHistory> withDateQuery(LocalDate startDate,LocalDate endDate,String leaveType);

	@Query("select p from LeaveHistory p where month(p.leaveStartDate)=?1 or month(p.leaveEndDate)=?1")
	List<LeaveHistory> withMonthQuery(int month);
	@Query(value="select * from leave_history where month(leave_start_date)=?1 or month(leave_end_date)=?1 limit ?2,?3",nativeQuery=true)
	List<LeaveHistory> withMonthQueryByPage(int month,int start,int perpage);
}
