package sg.edu.nus.lapsystem.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.nus.lapsystem.model.PublicHoliday;
import sg.edu.nus.lapsystem.repository.PublicHolidayRepository;

@Controller
public class MaintainPublicHolidayController {
	private PublicHolidayRepository PBrepository;
	private LocalDate LD;
    @Autowired
	public void setPBrepository(PublicHolidayRepository pBrepository) {
		this.PBrepository = pBrepository;
	}
//    @RequestMapping(path="/")
//	public String index() {
//        return null;
//    }
    @RequestMapping(path = "/publicholidays/create", method = RequestMethod.GET)
    public String createPublicHoliday(Model model) {
        model.addAttribute("publicholiday", new PublicHoliday());
        return "editpb";
    }
    @RequestMapping(path = "publicholidays", method = RequestMethod.POST)
    public String savePulicHoliday(PublicHoliday publicholiday) {
    	PBrepository.save(publicholiday);
    	return "redirect:/publicholidays";
    }

    @RequestMapping(path = "/publicholidays", method = RequestMethod.GET)
    public String getallPublicHoliday(Model model) {
    	model.addAttribute("publicholidays", PBrepository.findAll());
    	return "publicholidays";
    }
    
    @RequestMapping(path = "/publicholidays/editpb/{date}", method = RequestMethod.GET)
    public String editPublicHoliday(Model model,@PathVariable(value="date")LocalDate date) {
    	PublicHoliday PB=PBrepository.findById(date).orElse(null);
    	System.out.println(PB);
    	this.LD=date;
    	model.addAttribute("publicholiday", PB);
    	model.addAttribute("publicholiday.date", PB.getDate());
    	return "editpb";
    }
    @RequestMapping(path = "/publicholidays/delete/{date}", method = RequestMethod.GET)
    public String deletePubliHoliday(@PathVariable(name="date")LocalDate date) {
    	PublicHoliday pb=PBrepository.findById(date).orElse(null);
    	PBrepository.delete(pb);
    	return "redirect:/publicholidays";
    }



}
