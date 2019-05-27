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
		LocalDate ld2 = LocalDate.of(2019, 1, 7);
		LocalDate ld3 = LocalDate.of(2019, 1, 14);
		LocalDate ld4 = LocalDate.of(2019, 1, 31);
		LocalDate ld5 = LocalDate.of(2019, 2, 1);
		LocalDate ld6 = LocalDate.of(2019, 2, 14);
		LocalDate ld7 = LocalDate.of(2019, 3, 4);
		LocalDate ld8 = LocalDate.of(2019, 3, 6);
		LocalDate ld9 = LocalDate.of(2019, 3, 8);
		LocalDate ld10 = LocalDate.of(2019, 3, 11);
		LocalDate ld11 = LocalDate.of(2019, 4, 1);
		LocalDate ld12 = LocalDate.of(2019, 4, 2);
		LocalDate ld13 = LocalDate.of(2019, 4, 3);
		LocalDate ld14 = LocalDate.of(2019, 4, 4);
		LocalDate ld15 = LocalDate.of(2019, 6, 3);
		LocalDate ld16 = LocalDate.of(2019, 6, 4);
		LocalDate ld17 = LocalDate.of(2019, 6, 6);
		LocalDate ld18 = LocalDate.of(2019, 6, 7);


		
		
		// this has to change

		LeaveHistory lh1 = new LeaveHistory(e1, ld1, ld2, lc1, "I am getting married!!!","Applied");
		lh1 = lhs.InputData(lh1);
		LeaveHistory lh2 = new LeaveHistory(e1, ld1, ld3, lc2, "I am getting married!!!","Applied");
		lh2 = lhs.InputData(lh2);
		LeaveHistory lh3 = new LeaveHistory(e2, ld7, ld8, lc1, "zzz...","Updated");
		lh3 = lhs.InputData(lh3);
		LeaveHistory lh4 = new LeaveHistory(e2, ld1, ld5, lc1, "HoneyMoon!!!","Updated");
		lh4 = lhs.InputData(lh4);
		LeaveHistory lh5 = new LeaveHistory(e3, ld2, ld4, lc2, "see brother!!!","Applied");
		lh5 = lhs.InputData(lh5);
		LeaveHistory lh6 = new LeaveHistory(e3, ld9, ld10, lc2, "have cold!!!","Rejected");
		lh6 = lhs.InputData(lh6);
		LeaveHistory lh7 = new LeaveHistory(e4, ld7, ld9, lc1, "deal something!!!","Updated");
		lh7 = lhs.InputData(lh7);
		LeaveHistory lh8 = new LeaveHistory(e4, ld7, ld8, lc1, "go to school!!!","Rejected");
		lh8 = lhs.InputData(lh8);
		LeaveHistory lh9 = new LeaveHistory(e5, ld2, ld5, lc2, "have headache!!!","Applied");
		lh9 = lhs.InputData(lh9);
		LeaveHistory lh10 = new LeaveHistory(e5, ld1, ld5, lc2, "go to hospital!!!","Cancelled");
		lh10 = lhs.InputData(lh10);
		LeaveHistory lh11 = new LeaveHistory(e6, ld9, ld10, lc1, "I need moretime to chat with my little sises","Cancelled");
		lh11 = lhs.InputData(lh11);
		LeaveHistory lh12 = new LeaveHistory(e6, ld1, ld6, lc2, "I need moretime to chat with my little sises","Rejected");
		lh12 = lhs.InputData(lh12);
		LeaveHistory lh13 = new LeaveHistory(e7, ld8, ld10, lc2, "to relax!!!","Cancelled");
		lh13 = lhs.InputData(lh13);
		LeaveHistory lh14 = new LeaveHistory(e7, ld7, ld10, lc2, "too much pressure!!!","Rejected");
		lh14 = lhs.InputData(lh14);
		LeaveHistory lh15 = new LeaveHistory(e6, ld11, ld12, lc2, "LITTLE SIS MISSING ME!!!","Applied");
		lh15 = lhs.InputData(lh15);
		LeaveHistory lh16 = new LeaveHistory(e6, ld13, ld14, lc2, "LITTLE SIS MISSING ME!!!","Applied");
		lh16 = lhs.InputData(lh16);
		LeaveHistory lh17 = new LeaveHistory(e6, ld15, ld16, lc2, "LITTLE SIS MISSING ME!!!","Applied");
		lh17 = lhs.InputData(lh17);
		LeaveHistory lh18 = new LeaveHistory(e6, ld17, ld18, lc2, "LITTLE SIS MISSING ME!!!","Applied");
		lh18 = lhs.InputData(lh18);

		List<LeaveHistory> lhList = new ArrayList<LeaveHistory>();
		lhList.add(lh1);
		lhList.add(lh2);
		lhList.add(lh3);
		lhList.add(lh4);
		lhList.add(lh5);
		lhList.add(lh6);
		lhList.add(lh7);
		lhList.add(lh8);
		lhList.add(lh9);
		lhList.add(lh10);
		lhList.add(lh11);
		lhList.add(lh12);
		lhList.add(lh13);
		lhList.add(lh14);
		lhList.add(lh15);
		lhList.add(lh16);
		lhList.add(lh17);
		lhList.add(lh18);
		
		for (LeaveHistory lh : lhList) {
			lhs.save(lh);
			LOG.info(lh.toString());
		}

	}

}
