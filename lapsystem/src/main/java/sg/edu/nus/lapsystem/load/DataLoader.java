package sg.edu.nus.lapsystem.load;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import sg.edu.nus.lapsystem.model.Employee;
import sg.edu.nus.lapsystem.model.LeaveCategory;
import sg.edu.nus.lapsystem.model.LeaveHistory;
import sg.edu.nus.lapsystem.model.Position;
import sg.edu.nus.lapsystem.model.PublicHoliday;
import sg.edu.nus.lapsystem.service.EmployeeService;
import sg.edu.nus.lapsystem.service.LeaveCategoryService;
import sg.edu.nus.lapsystem.service.LeaveHistoryService;
import sg.edu.nus.lapsystem.service.PositionService;
import sg.edu.nus.lapsystem.service.PublicHolidayService;

//here is the switch

@Component
public class DataLoader implements CommandLineRunner {
	@Autowired
	private EmployeeService es;
	@Autowired
	private LeaveHistoryService lhs;
	@Autowired
	private PositionService ps;
	@Autowired
	private LeaveCategoryService lcs;
	@Autowired
	private PublicHolidayService phs;

	Logger LOG = LoggerFactory.getLogger(DataLoader.class);

	@Override
	public void run(String... args) throws Exception {

		LocalDate ldpb1 = LocalDate.of(2019, 1, 1);
		LocalDate ldpb2 = LocalDate.of(2019, 2, 6);
		LocalDate ldpb3 = LocalDate.of(2019, 4, 19);
		LocalDate ldpb4 = LocalDate.of(2019, 5, 1);
		LocalDate ldpb5 = LocalDate.of(2019, 5, 19);
		LocalDate ldpb6 = LocalDate.of(2019, 6, 5);
		LocalDate ldpb7 = LocalDate.of(2019, 8, 9);
		LocalDate ldpb8 = LocalDate.of(2019, 8, 11);
		LocalDate ldpb9 = LocalDate.of(2019, 11, 27);
		LocalDate ldpb10 = LocalDate.of(2019, 12, 25);

		PublicHoliday ph1 = new PublicHoliday(ldpb1, "New Year's Day");
		PublicHoliday ph2 = new PublicHoliday(ldpb2, "Chinese New Year");
		PublicHoliday ph3 = new PublicHoliday(ldpb3, "Good Friday");
		PublicHoliday ph4 = new PublicHoliday(ldpb4, "Labour Day");
		PublicHoliday ph5 = new PublicHoliday(ldpb5, "Vesak Day");
		PublicHoliday ph6 = new PublicHoliday(ldpb6, "Hari Raya Puasa");
		PublicHoliday ph7 = new PublicHoliday(ldpb7, "National Day");
		PublicHoliday ph8 = new PublicHoliday(ldpb8, "Hari Raya Haji");
		PublicHoliday ph9 = new PublicHoliday(ldpb9, "Deepavali");
		PublicHoliday ph10 = new PublicHoliday(ldpb10, "Christmas Day");

		List<PublicHoliday> phList = new ArrayList<PublicHoliday>();
		phList.add(ph1);
		phList.add(ph2);
		phList.add(ph3);
		phList.add(ph4);
		phList.add(ph5);
		phList.add(ph6);
		phList.add(ph7);
		phList.add(ph8);
		phList.add(ph9);
		phList.add(ph10);

		for (PublicHoliday ph : phList) {
			phs.save(ph);
			LOG.info(ph.toString());
		}

		Position adl1 = new Position("BIG BOSS", 365);
		Position adl2 = new Position("CFO", 60);
		Position adl3 = new Position("Project Manager", 30);
		Position adl4 = new Position("Programmer", 7);
		Position adl5 = new Position("Little sis chatting Consultant", 7);
		Position adl6 = new Position("Rookie Intern", 0);

		List<Position> pList = new ArrayList<Position>();
		pList.add(adl1);
		pList.add(adl2);
		pList.add(adl3);
		pList.add(adl4);
		pList.add(adl5);
		pList.add(adl6);

		for (Position p : pList) {
			ps.save(p);
			LOG.info(p.toString());
		}

		Employee e1 = new Employee("Woo Si Jie", adl1, null, "manager", "130");
		e1.setSupervisor(e1);
		Employee e2 = new Employee("Lester", adl2, e1, "employee", "129");
		Employee e3 = new Employee("Moe", adl3, e1, "manager", "128");
		Employee e4 = new Employee("SQ", adl3, e1, "manager", "127");
		Employee e5 = new Employee("ZC", adl4, e4, "employee", "126");
		Employee e6 = new Employee("KO", adl5, e4, "manager", "125");
		Employee e7 = new Employee("Muang", adl4, e3, "employee", "124");
		Employee e8 = new Employee("Steven", adl6, e6, "Administrator", "123");

		es.SetAnnualLeaveDays(e1);
		es.SetAnnualLeaveDays(e2);
		es.SetAnnualLeaveDays(e3);
		es.SetAnnualLeaveDays(e4);
		es.SetAnnualLeaveDays(e5);
		es.SetAnnualLeaveDays(e6);
		es.SetAnnualLeaveDays(e7);
		es.SetAnnualLeaveDays(e8);

		List<Employee> eList = new ArrayList<Employee>();
		eList.add(e1);
		eList.add(e2);
		eList.add(e3);
		eList.add(e4);
		eList.add(e5);
		eList.add(e6);
		eList.add(e7);
		eList.add(e8);

		for (Employee e : eList) {
			es.save(e);
			LOG.info(e.toString());
		}

		LeaveCategory lc1 = new LeaveCategory("Annual Leave");
		LeaveCategory lc2 = new LeaveCategory("Medical Leave");
		LeaveCategory lc3 = new LeaveCategory("Compensation Leave");

		List<LeaveCategory> lcList = new ArrayList<LeaveCategory>();
		lcList.add(lc1);
		lcList.add(lc2);
		lcList.add(lc3);

		for (LeaveCategory lc : lcList) {
			lcs.save(lc);
			LOG.info(lc.toString());
		}

		LocalDate ld1 = LocalDate.of(2019, 1, 2);
		LocalDate ld2 = LocalDate.of(2019, 1, 31);
		LocalDate ld3 = LocalDate.of(2019, 1, 7);
		LocalDate ld4 = LocalDate.of(2019, 1, 14);
		LocalDate ld5 = LocalDate.of(2019, 2, 1);
		LocalDate ld6 = LocalDate.of(2019, 3, 1);

		LeaveHistory lh1 = new LeaveHistory(e1, ld1, ld2, lc1, "I am getting married!!!");
		lh1 = lhs.InputData(lh1);
		LeaveHistory lh2 = new LeaveHistory(e6, ld1, ld3, lc2, "I need moretime to chat with my little sises");
		lh2 = lhs.InputData(lh2);
		LeaveHistory lh3 = new LeaveHistory(e4, ld1, ld4, lc1, "zzz...");
		lh3 = lhs.InputData(lh3);
		LeaveHistory lh4 = new LeaveHistory(e1, ld5, ld6, lc1, "HoneyMoon!!!");
		lh4 = lhs.InputData(lh4);

		List<LeaveHistory> lhList = new ArrayList<LeaveHistory>();
		lhList.add(lh1);
		lhList.add(lh2);
		lhList.add(lh3);
		lhList.add(lh4);

		for (LeaveHistory lh : lhList) {
			lhs.save(lh);
			LOG.info(lh.toString());
		}

	}

}
