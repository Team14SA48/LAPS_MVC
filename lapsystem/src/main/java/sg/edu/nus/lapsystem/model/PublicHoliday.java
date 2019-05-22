package sg.edu.nus.lapsystem.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PublicHoliday {
	@Id
	private Calendar date;
	@Column(nullable = false, length = 30)
	private String holidayName;
	
	//getter setter
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
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
	
	
	
	public PublicHoliday(Calendar date, String holidayName) {
		super();
		this.date = date;
		this.holidayName = holidayName;
	}
	@Override
	public String toString() {
		return "PublicHoliday [date=" + date.get(Calendar.DATE) + ","+  date.get(Calendar.MONTH) +","+date.get(Calendar.YEAR)+ ", holidayName=" + holidayName + "]";
	}
	
	
}
