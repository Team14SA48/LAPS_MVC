package sg.edu.nus.lapsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.lapsystem.model.LeaveCategory;

public interface LeaveCategoryRepository extends JpaRepository<LeaveCategory, String> {

}
