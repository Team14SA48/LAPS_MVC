package sg.edu.nus.lapsystem.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import sg.edu.nus.lapsystem.model.LeaveHistory;
import sg.edu.nus.lapsystem.service.LeaveHistoryService;


@Controller
@RequestMapping(path="Manager")
public class ManagerController {
	
	@Autowired
	private LeaveHistoryService lhs;
	
	@RequestMapping(path="ListLeaveRequests")
	public String listLeaveRequests(@CookieValue("userId") int userId,Model model) {
        
        model.addAttribute("LeaveRequests", lhs.listAppliedRequests(userId));
        return "LeaveRequests";
    }
	
	@RequestMapping(path="LeaveDetail")
	public String LeaveRequestsDetail(@CookieValue("userId") int userId,@RequestParam int leaveId,Model model) {
        model.addAttribute("LeaveRequest", lhs.findLeaveHistoryById(leaveId));
		return "RequestsDetail";
	}
	
	@RequestMapping(path="Approve")
	public String approveOrReject(@CookieValue("userId") int userId,@RequestParam int id,@RequestParam boolean isApprove,@RequestParam String rejectReason,Model model) {
		LeaveHistory lh = lhs.findLeaveHistoryById(id);
		if(isApprove) {
			lh.setStatus("Approved");
			
			
		}else if(!isApprove) {
			lh.setStatus("Rejected");
			lh.setRejectReason(rejectReason);
		}
		lhs.save(lh);
		model.addAttribute("LeaveRequests", lhs.listAppliedRequests(userId));
		return "LeaveRequests";
	}
}
