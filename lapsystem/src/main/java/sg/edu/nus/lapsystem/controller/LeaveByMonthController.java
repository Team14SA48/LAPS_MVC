package sg.edu.nus.lapsystem.controller;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.edu.nus.lapsystem.model.LeaveHistory;
import sg.edu.nus.lapsystem.model.Page;
import sg.edu.nus.lapsystem.model.PageInput;
import sg.edu.nus.lapsystem.repository.EmployeeRepository;
import sg.edu.nus.lapsystem.repository.LeaveHistoryRepository;
@SessionAttributes("showresult")
@Controller
public class LeaveByMonthController {
	LeaveHistoryRepository lhr;
	@Autowired
	private void setLhr(LeaveHistoryRepository lhr)
	{
		this.lhr=lhr;
	}
	EmployeeRepository er;
	@Autowired
	public void setEr(EmployeeRepository er) {
		this.er = er;
	}

	@RequestMapping(path="/viewleavebymonth",method=RequestMethod.GET)
	public String ViewLeaveByMonth()
	{
		
		return "choosetype";
	}
	@RequestMapping(path="/viewleavebymonth",method=RequestMethod.POST)
	public String ViewLeaveByMonth(@RequestParam("leavemonth")String monthType,@RequestParam("resultperpage")String resultNo,Model model)
	{
		PageInput input=new PageInput();
		input.setMonthType(monthType);
		input.setResultNo(Integer.parseInt(resultNo));
		model.addAttribute("showresult", input);
		
		return "redirect:/showleavebymonth";
	}
	
	
	
	
	@RequestMapping(path="/showleavebymonth")
	public String ViewLeaveByMonth(Integer page,Model model,@ModelAttribute("showresult") PageInput input)
	{
	
		int month=0;
		int currentMonth=LocalDate.now().getMonthValue();
		int previousMonth=currentMonth-1;
		int nextMonth=currentMonth+1;
	
		if(page==null)
			page=1;
		Page p=new Page();
		p.setPageSize(input.getResultNo());
		
		if(input.getMonthType().equals("Previous Month"))
		{	month=previousMonth;
		
		}
		else if(input.getMonthType().equals("Current Month"))
		{	month=currentMonth;

		}
		else if(input.getMonthType().equals("Next Month"))
			{month=nextMonth;
			
			}
	
		
		List<LeaveHistory> leaveList=lhr.withMonthQuery(month);
		p.setTotalLeaves(leaveList.size());
		p.setCurrentPage(page);
		//分页
		System.out.println(page);
		
		List<LeaveHistory> leaveListByPage=lhr.withMonthQueryByPage(month,(page-1)*p.getPageSize(),p.getPageSize());
		
	p.getTotalPages();
	System.out.println(p.getTotalPages());
		model.addAttribute("leaves", leaveListByPage);
		model.addAttribute("pages",p);
		model.addAttribute("length", p.getTotalPages());

		for(LeaveHistory leave:leaveListByPage)
		{
			System.out.println(leave.toString());
		}
	
		
		return "showleavebymonth";
		
	}

}
