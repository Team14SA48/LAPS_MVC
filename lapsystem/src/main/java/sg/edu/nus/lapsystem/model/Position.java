package sg.edu.nus.lapsystem.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Position {

	@Id
	@Column(length = 30, name = "positionName", unique = true, nullable = false)
	private String positionName;
	@Column(nullable = false)
	private int annualLeaveDays;
	@OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY, mappedBy="position")
	private Set<Employee> employees = new HashSet<Employee>();

	// getter setter

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public int getAnnualLeaveDays() {
		return annualLeaveDays;
	}

	public void setAnnualLeaveDays(int annualLeaveDays) {
		this.annualLeaveDays = annualLeaveDays;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	// constructor
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Position(String positionName, int annualLeaveDays) {
		super();
		this.positionName = positionName;
		this.annualLeaveDays = annualLeaveDays;
	}

	@Override
	public String toString() {
		return "Position [positionName=" + positionName + ", annualLeaveDays=" + annualLeaveDays
				+ ", NumberOfEmployees=" + employees.size() + "]";
	}

}