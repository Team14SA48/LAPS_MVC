package sg.edu.nus.lapsystem.repository;

import java.util.ArrayList;



import sg.edu.nus.lapsystem.model.CompensationClaimHistory;
import sg.edu.nus.lapsystem.model.Employee;

public interface CompensationServiceIF {

//	Employee authenticate(String name, String pwd);

	ArrayList<CompensationClaimHistory> findAllLeaves();

	CompensationClaimHistory findCompensation(int compensationId);

	CompensationClaimHistory changeCompensation(CompensationClaimHistory Compensation);

	void removeCompensation(CompensationClaimHistory Compensation);

}