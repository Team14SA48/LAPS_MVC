package sg.edu.nus.lapsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.lapsystem.model.LeaveHistory;
import sg.edu.nus.lapsystem.repository.LeaveHistoryRepository;

@Service
public class LeaveHistoryService {
	
	@Autowired
	private LeaveHistoryRepository lhr;

	public List<LeaveHistory> listAllAppliedRequests() {
		return lhr.findByStatus("Applied");
	}
	
	public LeaveHistory findLeaveHistoryById(int id) {
		return lhr.findById(id);
	}
}
