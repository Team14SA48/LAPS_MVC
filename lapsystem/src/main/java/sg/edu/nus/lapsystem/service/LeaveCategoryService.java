package sg.edu.nus.lapsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.lapsystem.model.LeaveCategory;
import sg.edu.nus.lapsystem.repository.LeaveCategoryRepository;

@Service
public class LeaveCategoryService {
	@Autowired
	LeaveCategoryRepository lcr;
	
	public LeaveCategory findByLeaveCategoryName(String leaveCategoryName) {
		return lcr.findByLeaveCategory(leaveCategoryName);
	}
}
