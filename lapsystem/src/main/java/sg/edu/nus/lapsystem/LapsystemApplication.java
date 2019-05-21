package sg.edu.nus.lapsystem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.lapsystem.load.DataLoader;
import sg.edu.nus.lapsystem.model.Employee;
import sg.edu.nus.lapsystem.model.LeaveHistory;
import sg.edu.nus.lapsystem.repository.EmployeeRepository;
import sg.edu.nus.lapsystem.repository.LeaveHistoryRepository;

@SpringBootApplication
public class LapsystemApplication  implements CommandLineRunner {
	
	
	@Autowired
	LeaveHistoryRepository lhr;
	@Autowired
	EmployeeRepository er;
	Logger LOG = LoggerFactory.getLogger(LapsystemApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LapsystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	}

}
