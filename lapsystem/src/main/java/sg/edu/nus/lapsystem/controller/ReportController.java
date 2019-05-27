package sg.edu.nus.lapsystem.controller;

//import java.io.File;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import sg.edu.nus.lapsystem.model.datainput;
import sg.edu.nus.lapsystem.repository.LeaveCategoryRepository;
import sg.edu.nus.lapsystem.repository.LeaveHistoryRepository;
import sg.edu.nus.lapsystem.util.CsvUtil;

@SessionAttributes("filter")
@Controller
public class ReportController {

	LeaveCategoryRepository lcr;

	@Autowired
	public void setLcr(LeaveCategoryRepository lcr) {
		this.lcr = lcr;
	}

	LeaveHistoryRepository lhr;

	@Autowired
	private void setLhr(LeaveHistoryRepository lhr) {
		this.lhr = lhr;
	}

	@RequestMapping(path = "/viewleavereport", method = RequestMethod.GET)
	public String ViewLeaveReport(Model model) {

		model.addAttribute("leavecategory", lcr.findAll());
		return "viewleavereport";
	}

	@RequestMapping(path = "/viewleavereport", method = RequestMethod.POST)
	public String ViewLeaveReport(@RequestParam("startdate") String startdate,
			@RequestParam("enddate") String enddate, Model model, @RequestParam("leavetype") String leavetype) {
//		Boolean flag=false;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate = LocalDate.parse(startdate, formatter);
		LocalDate endDate = LocalDate.parse(enddate, formatter);


		datainput input = new datainput();
		input.setStartDate(startDate);
		input.setEndDate(endDate);
		input.setLeaveType(leavetype);
		model.addAttribute("filter", input);
//    List<LeaveCategory> cList=lcr.findAll();
		// type为单个type
//    for(LeaveCategory c:cList)
//    {
//    	if(c.getLeaveCategory()==input.getLeaveType())
//    		flag=true;
//    }
		//
		if (leavetype.equals("All")) {
			model.addAttribute("leavereport", lhr.withDateQueryAll(input.getStartDate(), input.getEndDate()));
			System.out.println("print all");
		} else {
			model.addAttribute("leavereport",
					lhr.withDateQuery(input.getStartDate(), input.getEndDate(), input.getLeaveType()));
			System.out.println("print type");

		}
		model.addAttribute("leavecategory", lcr.findAll());
		return "viewleavereport";
	}

	@RequestMapping(path = "/exportreport", method = RequestMethod.GET)
	public String ExportReport(Model model, @ModelAttribute("filter") datainput data) throws IOException {
//		Boolean flag=false;
		List<LeaveHistory> list = null;
//		List<LeaveCategory> cList=lcr.findAll();
		// type为单个type
//	    for(LeaveCategory c:cList)
//	    {
//	    	if(c.getLeaveCategory()==data.getLeaveType())
//	    		flag=true;
//	    }
		if (data.getLeaveType().equals("All")) {
			list = lhr.withDateQueryAll(data.getStartDate(), data.getEndDate());

		} else {
			list = lhr.withDateQuery(data.getStartDate(), data.getEndDate(), data.getLeaveType());

		}
		for (LeaveHistory leave : list) {
			System.out.println(leave.toString());
		}

		File f = new File("C:\\Users\\steve\\Desktop\\LAPreport\\report.csv");
		if (f.exists()) {
			System.out.println("Yes Exist");
		} else {
			System.out.println("No, not exist");
			f.createNewFile();
			FileWriter resultFile = new FileWriter(f);
			resultFile.close();
		}
		if (f.exists()) {
			System.out.println("Yes Exist");
		} else {
			System.out.println("No, not exist");
		}
//		System.out.println(list.toString());
		boolean isSuccess = CsvUtil.exportCsv("C:\\Users\\steve\\Desktop\\LAPreport\\report.csv", list);
		System.out.println(isSuccess);
		return "successpage";
	}

}
