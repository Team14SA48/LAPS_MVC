package sg.edu.nus.lapsystem.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sg.edu.nus.lapsystem.model.Employee;
import sg.edu.nus.lapsystem.model.LeaveHistory;
import sg.edu.nus.lapsystem.repository.EmployeeRepository;
import sg.edu.nus.lapsystem.repository.LeaveHistoryRepository;
import sg.edu.nus.lapsystem.repository.LeaveService;
import sg.edu.nus.lapsystem.repository.LeaveServiceIF;
import sg.edu.nus.lapsystem.service.LeaveHistoryService;

@SessionAttributes("user")
@Controller
public class LeaveController {
	
	@Autowired
	LeaveHistoryService lhService;

	@Autowired
	private LeaveServiceIF lService;
	
	LeaveHistoryRepository lhr;
	@Autowired
	private void setLhr(LeaveHistoryRepository lhr)
	{
		this.lhr=lhr;
	}
	EmployeeRepository er;
	@Autowired
	private void setEr(EmployeeRepository er)
	{
		this.er=er;
	}
	
//	@RequestMapping(path="/login",method=RequestMethod.GET)
//	public ModelAndView Login()
//	{
//		ModelAndView mav=new ModelAndView("login");
//		mav.addObject("employee",new Employee());
//		return mav;
//	}
	
//	@RequestMapping(path="/authenticate",method=RequestMethod.POST)
//	public ModelAndView authenticateInfo (@ModelAttribute Employee employee,Model model)
//	{
//		ModelAndView mav=new ModelAndView("index");
//		Employee emp=lService.authenticate(employee.getName(), employee.getPassword());
//		//存储用户的值
//		mav.addObject("user", emp);
//		mav.addObject("leave", new LeaveHistory());
//		
//		return mav;
//	}
	
	@RequestMapping(path="/viewhistory",method=RequestMethod.GET)
	public String ViewAll(Model model,@CookieValue("userId")int userId,Employee emp)
	{

		model.addAttribute("leaveHistories",lhr.findByEmployee_Id(userId));
	
		return "viewhistory";
	}
	
	@RequestMapping(path="/viewdetails/{leaveid}",method=RequestMethod.GET)
	public String ViewDetails(Model model,@PathVariable(name = "leaveid") int leaveId)
	{
		
		model.addAttribute("leave", lhr.findById(leaveId));
	
		return "viewdetails";
	}
	
	@RequestMapping(path="/leave/edit/{leaveid}",method=RequestMethod.GET)
	public ModelAndView EditLeave(@PathVariable(name = "leaveid") int leaveId)
	{
		ModelAndView mav = new ModelAndView("editleave");
		LeaveHistory leave=lhr.findById(leaveId);
		mav.addObject("leave", leave);
		
//		String id=String.valueOf(leaveId);
//		mav.addObject("id", id);
		return mav;
		
	}
	
	@RequestMapping(path="/leave/edit/{leaveid}",method=RequestMethod.POST)
	public ModelAndView EditLeave(@ModelAttribute @Valid LeaveHistory leave, BindingResult result,
			@PathVariable(name = "leaveid") int leaveId,final RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return new ModelAndView("editleave");
		
		ModelAndView mav = new ModelAndView("redirect:/viewhistory");
		String message = "Leave was successfully updated.";
		LeaveHistory oldleave=lhr.findById(leaveId);
		oldleave.setLeaveStartDate(leave.getLeaveStartDate());
		oldleave.setLeaveEndDate(leave.getLeaveEndDate());
		oldleave.setAdditionalReasons(leave.getAdditionalReasons());
		oldleave.setLeaveDays(leave.getLeaveDays());
		oldleave.setContractDetail(leave.getContractDetail());
		oldleave.setWorkDissemination(leave.getWorkDissemination());
		
		lhService.CalculateLeaveDays(leave);
		oldleave.setLeaveDays(leave.getLeaveDays());
		
		oldleave.setStatus("Updated");
		lService.changeLeave(oldleave);
		

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
	
	
	@RequestMapping(path="/leave/cancel/{leaveid}",method=RequestMethod.GET)
	public String CancelLeave(@PathVariable(name = "leaveid") int leaveId)
	{
		LeaveHistory lh=lhr.findById(leaveId);
		lh.setStatus("Cancelled");
		lhr.save(lh);

		return "redirect:/viewhistory";
	}
	
	@RequestMapping(path="/leave/delete/{leaveid}",method=RequestMethod.GET)
	public String DeleteLeave(@PathVariable(name = "leaveid") int leaveId)
	{
		LeaveHistory lh=lhr.findById(leaveId);
	    lService.removeLeave(lh);
		return "redirect:/viewhistory";
	}
	
	
	
	

}
