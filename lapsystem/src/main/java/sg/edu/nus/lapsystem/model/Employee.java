package sg.edu.nus.lapsystem.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 30)
	private String name;
	@ManyToOne
	private Position position;
	@ManyToOne
	private Employee supervisor;
	@OneToMany(mappedBy = "supervisor")
	private Set<Employee> subordinates = new HashSet<Employee>();
	@Column(nullable = false)
	private int annualLeaveDaysLeft;
	@Column(nullable = false)
	private int medicalLeaveDaysLeft;
	@Column(nullable = false, length = 30)
	private String role;
	@Column(nullable = false, length = 30)
	private String password;
	@OneToMany(mappedBy = "employee")
	private Set<LeaveHistory> LeaveHistories = new HashSet<LeaveHistory>();

	// Constant

	private final static int MEDICAL_LEAVEDAYS = 60;

	// getter setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
		position.getEmployees().add(this);
	}

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
		if (supervisor != null)
			supervisor.getSubordinates().add(this);
	}

	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public int getAnnualLeaveDaysLeft() {
		return annualLeaveDaysLeft;
	}

	public void setAnnualLeaveDaysLeft(int annualLeaveDaysLeft) {
		this.annualLeaveDaysLeft = annualLeaveDaysLeft;
	}

	public int getMedicalLeaveDaysLeft() {
		return medicalLeaveDaysLeft;
	}

	public void setMedicalLeaveDaysLeft(int medicalLeaveDaysLeft) {
		this.medicalLeaveDaysLeft = medicalLeaveDaysLeft;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<LeaveHistory> getLeaveHistories() {
		return LeaveHistories;
	}

	public void setLeaveHistories(Set<LeaveHistory> leaveHistories) {
		LeaveHistories = leaveHistories;
	}

	// constructor
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String name, Position position, Employee supervisor, String role, String password) {
		super();
		this.name = name;
		this.position = position;
		position.getEmployees().add(this);
		this.supervisor = supervisor;
		if (supervisor != null)
			supervisor.getSubordinates().add(this);
		medicalLeaveDaysLeft = MEDICAL_LEAVEDAYS;
		this.role = role;
		this.password = password;

	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", position=" + position.getAnnualLeaveDays() + ", supervisor=" + supervisor.getName()
				+ ", NumberOfSubordinates=" + subordinates.size() + ", annualLeaveDaysLeft=" + annualLeaveDaysLeft
				+ ", medicalLeaveDaysLeft=" + medicalLeaveDaysLeft + ", role=" + role + ", password=" + password
				+ ", NumberOfLeaveHistories=" + LeaveHistories.size() + "]";
	}

}
