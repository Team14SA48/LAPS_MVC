package sg.edu.nus.lapsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.lapsystem.model.LeaveHistory;
import sg.edu.nus.lapsystem.service.EmployeeService;
import sg.edu.nus.lapsystem.service.LeaveHistoryService;

@Controller
public class MenuController {
	
	@Autowired
	private EmployeeService es;
	@Autowired
	private LeaveHistoryService lhs;
	
	@RequestMapping(path = "menu")
	public String Menu(@CookieValue("userId")int userId,Model model) {
		model.addAttribute("User", es.findById(userId));
		model.addAttribute("LeaveHistory", lhs.findByEmployeeId(userId));
		return "menu";
	}
}
