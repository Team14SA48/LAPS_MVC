package sg.edu.nus.lapsystem.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.edu.nus.lapsystem.model.CompensationClaimHistory;
import sg.edu.nus.lapsystem.model.Employee;

import sg.edu.nus.lapsystem.repository.CompensationClaimHistoryRepository;
import sg.edu.nus.lapsystem.repository.EmployeeRepository;
import sg.edu.nus.lapsystem.util.CsvUtil;

@SessionAttributes("employeechoosed")
@Controller
public class CompensationReportController {
	
	CompensationClaimHistoryRepository ccr;

	@Autowired
	public void setCcr(CompensationClaimHistoryRepository ccr) {
		this.ccr = ccr;
	}
	
	EmployeeRepository er;
	
	@Autowired
	public void setEr(EmployeeRepository er) {
		this.er = er;
	}


	@RequestMapping(path="/viewcompensationreport",method=RequestMethod.GET)
	public String ViewCompensationReport(Model model)
	{
		
		model.addAttribute("employee", er.findAll());
		return "ViewCompensationReport";
	}
	
	@RequestMapping(path="/viewcompensationreport",method=RequestMethod.POST)
	public String ViewCompensationReport(@RequestParam("employeename") String employeeName,Model model)
	{
		Employee employeeChoosed=er.findByName(employeeName);
		model.addAttribute("compensations", ccr.findAllByEmployee(employeeChoosed));
		model.addAttribute("employeechoosed", employeeChoosed);
		return "ViewCompensationReport";
		
	}
	
	@RequestMapping(path="/exportcompensationreport",method=RequestMethod.GET)
	public String ExportReport(Model model,@ModelAttribute("employeechoosed")Employee employeechoosed ) throws IOException
	{
		List<CompensationClaimHistory> list=ccr.findAllByEmployee(employeechoosed);
		File f = new File("C:\\Users\\steve\\Desktop\\LAPreport\\compensationreport.csv");
		if(f.exists()) {System.out.println("Yes Exist");}
		else {
			System.out.println("No, not exist");
			f.createNewFile();
		FileWriter resultFile = new FileWriter(f);
		resultFile.close();}
		if(f.exists()) {System.out.println("Yes Exist");}
		else {System.out.println("No, not exist");}
		System.out.println(list.toString());
	boolean isSuccess=CsvUtil.exportCompensationCsv("C:\\Users\\steve\\Desktop\\LAPreport\\compensationreport.csv", list);
	System.out.println(isSuccess);
	return "successpage";
	}
	
	

}
