package sg.edu.nus.lapsystem.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.lapsystem.model.Employee;
import sg.edu.nus.lapsystem.model.LeaveCategory;
import sg.edu.nus.lapsystem.model.LeaveHistory;
import sg.edu.nus.lapsystem.service.EmployeeService;
import sg.edu.nus.lapsystem.service.LeaveCategoryService;
import sg.edu.nus.lapsystem.service.LeaveHistoryService;

@Controller
@SessionAttributes("User")
@RequestMapping(path="Employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService er;
	@Autowired
	private LeaveCategoryService lcs;
	@Autowired
	private LeaveHistoryService lhs;
	
	public EmployeeController() {
		er = new EmployeeService();
		lcs = new LeaveCategoryService();
		lhs = new LeaveHistoryService();
	}
	
	@RequestMapping(path="ApplyLeave")
	public String ApplyLeaveForm(Model model) {
        model.addAttribute("Leave", new LeaveHistory());
        model.addAttribute("LeaveRequestList",lhs.findAll());
        // mock up one, this will be provided by log in controller
        model.addAttribute("User", er.findById(1));
		return "applyLeaveForm";
    }
	
	@RequestMapping(path="submit")
	public ModelAndView Submit(Model model,@RequestParam String leaveCategory,@RequestParam String leaveStartDate,@RequestParam String leaveEndDate,@RequestParam String additionalReasons,@RequestParam String workDissemination,@RequestParam boolean ifOverseas) {
		
		System.out.println(ifOverseas);
		
		LocalDate ld1 = LocalDate.parse(leaveStartDate);
		LocalDate ld2 = LocalDate.parse(leaveEndDate);

		LeaveCategory lc = lcs.findByLeaveCategoryName(leaveCategory);
		
		Employee e1 = (Employee) model.asMap().get("User");
		
		LeaveHistory lh = new LeaveHistory(e1,ld1,ld2,lc,additionalReasons,"Applied");

		lhs.save(lh);
		
		return new ModelAndView("redirect:/Employee/ApplyLeave");
	}
	
}
