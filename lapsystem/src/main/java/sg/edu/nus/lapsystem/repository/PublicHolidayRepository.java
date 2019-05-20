package sg.edu.nus.lapsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.lapsystem.model.PublicHoliday;

public interface PublicHolidayRepository extends JpaRepository<PublicHoliday, Integer>{

}
