package sg.edu.nus.lapsystem.model;

import java.time.LocalDate;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class PublicHoliday {
	@Id
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	@Column(nullable = false, length = 30)
	private String holidayName;
	
	//getter setter
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	
	//constructor
	public PublicHoliday() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public PublicHoliday(LocalDate date, String holidayName) {
		super();
		this.date = date;
		this.holidayName = holidayName;
	}
	@Override
	public String toString() {
		return "PublicHoliday [date=" + date + ", holidayName=" + holidayName + "]";
	}

	
	
}
