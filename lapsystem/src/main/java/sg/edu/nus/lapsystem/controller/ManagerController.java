package sg.edu.nus.lapsystem.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import sg.edu.nus.lapsystem.model.LeaveHistory;
import sg.edu.nus.lapsystem.service.LeaveHistoryService;


@Controller
@SessionAttributes("user")
@RequestMapping(path="Manager")
public class ManagerController {
	
	@Autowired
	private LeaveHistoryService lhs;
	
	public ManagerController() {
		lhs = new LeaveHistoryService();
		
	}
	
	@RequestMapping(path="LeaveRequests")
	public String showLeaveRequests(Model model) {
        
        // this will be delete later
        model.addAttribute("user", 1);
        model.addAttribute("LeaveRequestList", lhs.listAppliedRequests((int)model.asMap().get("user")));
        return "LeaveRequests";
    }
	
	@RequestMapping(path="LeaveRequestDetail")
	public String showLeaveRequestDetail(@RequestParam int id,Model model) {
		model.addAttribute("LeaveHistory",lhs.findLeaveHistoryById(id));
        return "LeaveRequestDetail";
    }
	
	@RequestMapping(path="ApproveOrReject")
	public ModelAndView approveOrReject(@RequestParam int id,@RequestParam boolean approveOrNot,@RequestParam String rejectReason,Model model) {
		LeaveHistory lh = lhs.findLeaveHistoryById(id);
		if(approveOrNot) {
			//lh.setStatus("Approved");
			lhs.save(lh);
			
		}else if(!approveOrNot) {
			//lh.setStatus("Rejected");
			lh.setRejectReason(rejectReason);
			lhs.save(lh);
		}
		return new ModelAndView("redirect:/Manager/LeaveRequests");
	}
}
