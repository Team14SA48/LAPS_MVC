package sg.edu.nus.lapsystem.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.lapsystem.model.CompensationClaimHistory;
import sg.edu.nus.lapsystem.model.Employee;


@Service
public class CompensationService implements CompensationServiceIF {
	
//	@Autowired
//	private EmployeeRepository eRepo;
	
	@Resource
	private CompensationClaimHistoryRepository ccr;
	
//	@Override
//	@Transactional
//	public Employee authenticate(String name,String pwd)
//	{
//		List<Employee> eList=eRepo.findByempNameAndPwd(name, pwd);
//		return eList.get(0);
//	}
//	

	@Override
	@Transactional
	public ArrayList<CompensationClaimHistory> findAllLeaves() {
		ArrayList<CompensationClaimHistory> history = (ArrayList<CompensationClaimHistory>) ccr.findAll();
		return history;
	}
	

	@Override
	@Transactional
	public CompensationClaimHistory findCompensation(int compensationId) {
		return ccr.findById(compensationId);

	}


	@Override
	@Transactional
	public CompensationClaimHistory changeCompensation(CompensationClaimHistory Compensation) {
		return ccr.saveAndFlush(Compensation);
	}
	

	@Override
	@Transactional
	public void removeCompensation(CompensationClaimHistory Compensation) {
		ccr.delete(Compensation);
	}


}
