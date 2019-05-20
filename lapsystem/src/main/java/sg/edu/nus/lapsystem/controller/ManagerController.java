package sg.edu.nus.lapsystem.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.lapsystem.service.LeaveHistoryService;


@Controller
@RequestMapping(path="Manager")
public class ManagerController {
	
	@Autowired
	private LeaveHistoryService lhs;
	
	public ManagerController() {
		lhs = new LeaveHistoryService();
	}
	
	@RequestMapping(path="LeaveRequests")
	public String showLeaveRequests(Model model) {
        model.addAttribute("LeaveRequestList", lhs.listAllAppliedRequests());
        return "LeaveRequests";
    }
	
	@RequestMapping(path="LeaveRequestDetail")
	public String showLeaveRequestDetail(@RequestParam int id,Model model) {
		model.addAttribute("LeaveHistory",lhs.findLeaveHistoryById(id));
        return "LeaveRequestDetail";
    }
}
