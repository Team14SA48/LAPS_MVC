package sg.edu.nus.lapsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.lapsystem.model.PublicHoliday;
import sg.edu.nus.lapsystem.repository.PublicHolidayRepository;

@Service
public class PublicHolidayService {
	@Autowired
	PublicHolidayRepository phr;
	
	public List<PublicHoliday> findAll(){
		return phr.findAll();
	}
}
