package sg.edu.nus.lapsystem.load;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import sg.edu.nus.lapsystem.model.Employee;
import sg.edu.nus.lapsystem.model.LeaveCategory;
import sg.edu.nus.lapsystem.model.LeaveHistory;
import sg.edu.nus.lapsystem.model.Position;
import sg.edu.nus.lapsystem.model.PublicHoliday;
import sg.edu.nus.lapsystem.repository.EmployeeRepository;
import sg.edu.nus.lapsystem.repository.LeaveCategoryRepository;
import sg.edu.nus.lapsystem.repository.LeaveHistoryRepository;
import sg.edu.nus.lapsystem.repository.PositionRepository;
import sg.edu.nus.lapsystem.repository.PublicHolidayRepository;
import sg.edu.nus.lapsystem.service.EmployeeService;

//here is the switch

//@Component
public class DataLoader implements CommandLineRunner {
	@Autowired
	private LeaveHistoryRepository lhr;
	@Autowired
	private PositionRepository pr;
	@Autowired
	private EmployeeRepository er;
	@Autowired
	private LeaveCategoryRepository lcr;
	@Autowired
	private PublicHolidayRepository phr;
	@Autowired
	private EmployeeService es;
	
	
	Logger LOG = LoggerFactory.getLogger(DataLoader.class);
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Position adl1 = new Position("BIG BOSS",365);
		Position adl2 = new Position("CFO",60);
		Position adl3 = new Position("Project Manager",30);
		Position adl4 = new Position("Programmer",7);
		Position adl5 = new Position("Little sis chatting Consultant",7);
		Position adl6 = new Position("Rookie Intern",0);

		pr.save(adl1);
		pr.save(adl2);
		pr.save(adl3);
		pr.save(adl4);
		pr.save(adl5);
		pr.save(adl6);

		
		
		Employee e1 =  new Employee("Woo Si Jie",adl1,null,"manager","130");
		e1.setSupervisor(e1);
		Employee e2 =  new Employee("Lester",adl2,e1,"employee","129");
		Employee e3 =  new Employee("Moe",adl3,e1,"manager","128");
		Employee e4 =  new Employee("SQ",adl3,e1,"manager","127");
		Employee e5 =  new Employee("ZC",adl4,e4,"employee","126");
		Employee e6 =  new Employee("KO",adl5,e4,"manager","125");
		Employee e7 =  new Employee("Muang",adl4,e3,"employee","124");
		Employee e8 =  new Employee("Steven",adl6,e6,"Administrator","123");
		
		es.SetAnnualLeaveDays(e1);
		es.SetAnnualLeaveDays(e2);
		es.SetAnnualLeaveDays(e3);
		es.SetAnnualLeaveDays(e4);
		es.SetAnnualLeaveDays(e5);
		es.SetAnnualLeaveDays(e6);
		es.SetAnnualLeaveDays(e7);
		es.SetAnnualLeaveDays(e8);

		er.save(e1);
		er.save(e2);
		er.save(e3);
		er.save(e4);
		er.save(e5);
		er.save(e6);
		er.save(e7);
		er.save(e8);
		
		LeaveCategory lc1 = new LeaveCategory("annual leave");
		LeaveCategory lc2 = new LeaveCategory("medical leave");
		LeaveCategory lc3 = new LeaveCategory("compensation leave");

		lcr.save(lc1);
		lcr.save(lc2);
		lcr.save(lc3);
		
		LocalDate ld1 = LocalDate.of(2019, 7, 1);
		LocalDate ld2 = LocalDate.of(2019, 8, 1);
		LocalDate ld3 = LocalDate.of(2019, 9, 1);
		
		LeaveHistory lh1 = new LeaveHistory(e1,ld1,ld1.plusDays(30),lc1,"I am getting married!!!","Applied");
		LeaveHistory lh2 = new LeaveHistory(e6,ld2,ld2.plusDays(1),lc1,"I need more time to chat with my little sises","Rejected");
		LeaveHistory lh3 = new LeaveHistory(e4,ld3,ld3.plusDays(2),lc1,"zzz...","Approved");
		LeaveHistory lh4 = new LeaveHistory(e1,ld1.plusDays(30),ld1.plusDays(60).plusDays(30),lc1,"HoneyMoon!!!","Applied");


		lhr.save(lh1);
		lhr.save(lh2);
		lhr.save(lh3);
		lhr.save(lh4);
		
		LocalDate ld4 = LocalDate.of(2019,1,1);
		
		PublicHoliday ph1 = new PublicHoliday(ld4,"New Year's Day");
		
		phr.save(ph1);
		
		LOG.info(adl1.toString());
		LOG.info(adl2.toString());
		LOG.info(adl3.toString());
		
		LOG.info(e1.toString());
		LOG.info(e2.toString());
		LOG.info(e3.toString());
		LOG.info(e4.toString());
		LOG.info(e5.toString());
		LOG.info(e6.toString());
		LOG.info(e7.toString());
		LOG.info(e8.toString());

		LOG.info(lc1.toString());
		LOG.info(lc2.toString());
		LOG.info(lc3.toString());
		
		LOG.info(lh1.toString());
		LOG.info(lh2.toString());
		LOG.info(lh3.toString());
		
	}
	
}
