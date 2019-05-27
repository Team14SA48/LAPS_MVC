package sg.edu.nus.lapsystem.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.lapsystem.model.Employee;
import sg.edu.nus.lapsystem.model.LeaveCategory;
import sg.edu.nus.lapsystem.model.LeaveHistory;
import sg.edu.nus.lapsystem.model.PublicHoliday;
import sg.edu.nus.lapsystem.repository.LeaveHistoryRepository;

@Service
public class LeaveHistoryService {

	@Autowired
	private LeaveHistoryRepository lhr;
	@Autowired
	private EmployeeService es;
	@Autowired
	private PublicHolidayService phs;

	// Save

	public void save(LeaveHistory lh) {
		es.save(lh.getEmployee());
		lhr.saveAndFlush(lh);
	}

	// Create

	// this is will complete form and do validation
	public LeaveHistory CompleteAndValidateForm(LeaveHistory leaveForm) {
		LocalDate leaveStartDate = leaveForm.getLeaveStartDate();
		LocalDate leaveEndDate = leaveForm.getLeaveEndDate();
		Employee employee = leaveForm.getEmployee();
		LocalDate now = LocalDate.now();
		leaveForm.setSubmitDate(now);
		LeaveCategory leaveCategory = leaveForm.getLeaveCategory();

		DateValidator(leaveStartDate, leaveEndDate);
		WeekendValidator(leaveStartDate, leaveEndDate);
		SubmitDateValidator(leaveStartDate, now, 0);
		HolidaysValidator(leaveStartDate, leaveEndDate);
		OverLapValidator(leaveStartDate, leaveEndDate, employee);
		CalculateLeaveDays(leaveForm);
		deductLeavedaysLeft(employee, leaveCategory, leaveForm.getLeaveDays());
		return leaveForm;
	}

	public LeaveHistory InputData(LeaveHistory leaveHistory) {
		LocalDate leaveStartDate = leaveHistory.getLeaveStartDate();
		LocalDate leaveEndDate = leaveHistory.getLeaveEndDate();
		Employee employee = leaveHistory.getEmployee();
		LocalDate now = LocalDate.now();
		leaveHistory.setSubmitDate(now);
		LeaveCategory leaveCategory = leaveHistory.getLeaveCategory();

		DateValidator(leaveStartDate, leaveEndDate);
		WeekendValidator(leaveStartDate, leaveEndDate);
		HolidaysValidator(leaveStartDate, leaveEndDate);
		OverLapValidator(leaveStartDate, leaveEndDate, employee);
		CalculateLeaveDays(leaveHistory);
		deductLeavedaysLeft(employee, leaveCategory, leaveHistory.getLeaveDays());
		return leaveHistory;
	}

	// Retrieve

	// Find all record
	public List<LeaveHistory> findAll() {
		return lhr.findAllByOrderByIdDesc();
	}

	public List<LeaveHistory> findByEmployeeId(int id) {
		return lhr.findByEmployee_IdOrderByIdDesc(id);
	}

	// List all Requests with status "Applied"
	public List<LeaveHistory> listAllAppliedRequests() {
		return lhr.findByStatus("Applied");
	}

	// Find all Requests with status "Applied" under specific manager
	public List<LeaveHistory> listAppliedRequests(int ManagerId) {
		Set<Integer> ids = new HashSet<Integer>();
		List<Employee> empList = es.findBySupervisor_Id(ManagerId);
		for (Employee e : empList) {
			ids.add(e.getId());
		}
		return lhr.findByStatusAndEmployee_IdIn("Applied", ids);
	}

	// Find specific Request by History_Id
	public LeaveHistory findLeaveHistoryById(int id) {
		return lhr.findById(id);
	}

	// Update

	public void update(LeaveHistory lh) {
		lhr.delete(lh);
		
		String leaveCategory = lh.getLeaveCategory().getLeaveCategory();
		int leaveDays = lh.getLeaveDays();
		if(leaveCategory.equals("Annual Leave"))
			lh.getEmployee().setAnnualLeaveDaysLeft(lh.getEmployee().getAnnualLeaveDaysLeft()+leaveDays);
		if(leaveCategory.equals("Medical Leave"))
			lh.getEmployee().setMedicalLeaveDaysLeft(lh.getEmployee().getMedicalLeaveDaysLeft()+leaveDays);
		es.save(lh.getEmployee());
		
		LeaveHistory leave = CompleteAndValidateForm(lh);
		lhr.saveAndFlush(lh);
	}
	
	//D
	
	// Calculator

	public LeaveHistory CalculateLeaveDays(LeaveHistory lh) {
		LocalDate startDate = lh.getLeaveStartDate();
		LocalDate endDate = lh.getLeaveEndDate();
		int leaveDays = (int) (ChronoUnit.DAYS.between(startDate, endDate) + 1);
		if (leaveDays <= 14) {
			leaveDays -= excludeWeekend(startDate, endDate);
			leaveDays -= excludeHolidays(startDate, endDate);
		}
		lh.setLeaveDays(leaveDays);
		return lh;
	}

	private Employee deductLeavedaysLeft(Employee employee, LeaveCategory leaveCategory, int leaveDays)
			throws IllegalArgumentException {

		if (leaveCategory.getLeaveCategory().equals("Annual Leave")) {
			int leftDays = employee.getAnnualLeaveDaysLeft() - leaveDays;
			if (leftDays < 0)
				throw new IllegalArgumentException("not enough Annual Leave days left");
			else
				employee.setAnnualLeaveDaysLeft(leftDays);
		} else if (leaveCategory.getLeaveCategory().equals("Medical Leave")) {
			int leftDays = employee.getMedicalLeaveDaysLeft() - leaveDays;
			if (leftDays < 0)
				throw new IllegalArgumentException("not enough Annual Leave days left");
			else
				employee.setMedicalLeaveDaysLeft(leftDays);
		} else
			throw new IllegalArgumentException("Leave category not exist");
		return employee;

	}

	private int excludeWeekend(LocalDate startDate, LocalDate endDate) {
		int deductDays = 0;
		int leaveDays = (int) (ChronoUnit.DAYS.between(startDate, endDate) + 1);
		deductDays += leaveDays / 7 * 2;
		if (endDate.getDayOfWeek().getValue() - startDate.getDayOfWeek().getValue() < 0)
			deductDays += 2;
		return deductDays;
	}

	private int excludeHolidays(LocalDate startDate, LocalDate endDate) {
		int deductDays = 0;
		List<PublicHoliday> phList = phs.findAll();
		for (PublicHoliday ph : phList) {
			if (1 <= ph.getDate().getDayOfWeek().getValue() && ph.getDate().getDayOfWeek().getValue() <= 5)
				if (startDate.getDayOfYear() <= ph.getDate().getDayOfYear()
						&& endDate.getDayOfYear() >= ph.getDate().getDayOfYear())
					deductDays++;
		}
		return deductDays;
	}

	// Validators

	// here have to add holiday validation
	private Boolean HolidaysValidator(LocalDate startDate, LocalDate endDate) throws IllegalArgumentException {
		List<PublicHoliday> phList = phs.findAll();
		for (PublicHoliday ph : phList) {
			if (ph.getDate().isEqual(startDate))
				throw new IllegalArgumentException("start date can not on public holiday");
			else if (ph.getDate().isEqual(endDate))
				throw new IllegalArgumentException("end date can not on public holiday");
		}
		return true;
	}

	// this will check if start date or end date is on weekend
	private Boolean WeekendValidator(LocalDate startDate, LocalDate endDate) throws IllegalArgumentException {
		if (startDate.getDayOfWeek() == DayOfWeek.SATURDAY || startDate.getDayOfWeek() == DayOfWeek.SUNDAY)
			throw new IllegalArgumentException("Start date can not on weekend");
		else if (endDate.getDayOfWeek() == DayOfWeek.SATURDAY || endDate.getDayOfWeek() == DayOfWeek.SUNDAY)
			throw new IllegalArgumentException("End date can not on weekend");
		else
			return true;
	}

	// this will check if submit date is too late related to start date
	private Boolean SubmitDateValidator(LocalDate startDate, LocalDate submitDate, int dayBeforeLimit)
			throws IllegalArgumentException {
		if (!startDate.isAfter(submitDate.plusDays(dayBeforeLimit - 1)))
			throw new IllegalArgumentException("Submit date should be " + dayBeforeLimit + " day before start date");
		else
			return true;
	}

	private Boolean OverLapValidator(LocalDate startDate, LocalDate endDate, Employee employee)
			throws IllegalArgumentException {
		List<LeaveHistory> lhList = lhr.findByEmployee(employee);

		lhList.removeIf(lh -> (lh.getStatus() == "Rejected"));
		lhList.removeIf(lh -> (lh.getStatus() == "Deleted"));
		lhList.removeIf(lh -> (lh.getStatus() == "Cancel"));
		for (LeaveHistory lh : lhList) {
			if (!lh.getLeaveStartDate().isAfter(endDate) && !lh.getLeaveEndDate().isBefore(startDate))
				throw new IllegalArgumentException("Duration Overlap to history");
		}
		return true;
	}

	// this will check if start date is before or equal end date
	private Boolean DateValidator(LocalDate startDate, LocalDate endDate) throws IllegalArgumentException {
		if (startDate.isAfter(endDate))
			throw new IllegalArgumentException("Start date can not be after end date");
		else
			return true;
	}

}
