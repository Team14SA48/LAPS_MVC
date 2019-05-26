package sg.edu.nus.lapsystem.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import sg.edu.nus.lapsystem.Access.UserAccess;
import sg.edu.nus.lapsystem.model.Employee;
import sg.edu.nus.lapsystem.model.LeaveCategory;
import sg.edu.nus.lapsystem.model.LeaveHistory;
import sg.edu.nus.lapsystem.service.EmployeeService;
import sg.edu.nus.lapsystem.service.LeaveCategoryService;
import sg.edu.nus.lapsystem.service.LeaveHistoryService;

@Controller
@RequestMapping(path = "Employee")
public class EmployeeController {

	@Autowired
	private EmployeeService es;
	@Autowired
	private LeaveCategoryService lcs;
	@Autowired
	private LeaveHistoryService lhs;

	@RequestMapping(path = "ApplyLeave")
	public String ApplyLeaveForm(@CookieValue("userId") int userId, Model model) {
		model.addAttribute("leaveForm", new LeaveHistory());
		model.addAttribute("User", es.findById(userId));
		model.addAttribute("HistoryLeaveList", lhs.findByEmployeeId(userId));
		
		UserAccess userAccess = new UserAccess(es.findById(userId));
		model.addAttribute("UserAccess", userAccess);
		
		return "applyLeaveForm";
	}

	@RequestMapping(path = "submit")
	public String Submit(@ModelAttribute @Valid LeaveHistory leaveform, BindingResult bindingResult,
			@RequestParam boolean ifOverseas, @CookieValue("userId") int userId, Model model) {

		leaveform.setEmployee(es.findById(userId));
		UserAccess userAccess = new UserAccess(es.findById(userId));
		model.addAttribute("UserAccess", userAccess);
		
		try {
			// additional validation here
			if (ifOverseas && leaveform.getContractDetail().equals(""))
				throw new IllegalArgumentException("Please input Contract Detail when going overseas");

			LeaveHistory leave = lhs.CompleteAndValidateForm(leaveform);
			leaveform.setStatus("Applied");
			lhs.save(leave);
			
			model.addAttribute("leaveForm", new LeaveHistory());

		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			
			model.addAttribute("ErrorMessage", iae.getMessage());
			model.addAttribute("leaveForm", leaveform);
		}
		
		model.addAttribute("HistoryLeaveList", lhs.findByEmployeeId(userId));
		model.addAttribute("User", es.findById(userId));
		
		return "applyLeaveForm";
	}

	@RequestMapping(path = "LeaveDetail")
	public String showLeaveRequestDetail(@CookieValue("userId") int userId, @RequestParam int leaveId, Model model) {
		model.addAttribute("LeaveHistory", lhs.findLeaveHistoryById(leaveId));
		
		UserAccess userAccess = new UserAccess(es.findById(userId));
		model.addAttribute("UserAccess", userAccess);

		return "LeaveDetail";
	}

}
