package sg.edu.nus.lapsystem.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.edu.nus.lapsystem.model.LeaveHistory;

@Controller
@SessionAttributes("user")
@RequestMapping(path="Employee")
public class EmployeeController {
	
	@RequestMapping(path="ApplyLeave")
	public String ApplyLeaveForm(Model model) {
        model.addAttribute("LeaveHistory", new LeaveHistory());
		return "applyLeaveForm";
    }
	
	@RequestMapping(path="submit")
	public String Submit(Model model,@RequestParam String leaveCategory,@RequestParam String leaveStartDate,@RequestParam String leaveEndDate,@RequestParam String additionalReasons,@RequestParam String workDissemination,@RequestParam String contractDetail) {
		System.out.println(leaveCategory);
		System.out.println(leaveStartDate);
		System.out.println(leaveEndDate);
		System.out.println(additionalReasons);
		System.out.println(workDissemination);
		System.out.println(contractDetail);

		
		return "test";
	}
	
}
