package sg.edu.nus.lapsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.nus.lapsystem.model.Position;
import sg.edu.nus.lapsystem.repository.PositionRepository;

@Controller
@RequestMapping(path="LeaveType")
public class LeaveTypeController {
	
	private PositionRepository leaveTypeRepository;
	
	@Autowired
	public void setLeaveTypeRepository(PositionRepository leaveTypeRepository) {
		this.leaveTypeRepository = leaveTypeRepository;
	}

    @RequestMapping(path = "/leavetypes/add", method = RequestMethod.GET)
    public String createLeaveType(Model model) {
        model.addAttribute("leavetype", new Position());
        return "createLeaveType";
    }

    @RequestMapping(path = "leavetypes", method = RequestMethod.POST)
    public String saveLeaveType(@Valid Position leavetype, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		return "createLeaveType";
    	}
    	leaveTypeRepository.save(leavetype);
        return "redirect:/LeaveType/leavetypes";
    }
    

    @RequestMapping(path = "/leavetypes", method = RequestMethod.GET)
    public String getAllLeavetypes(Model model) {
        model.addAttribute("leavetypes", leaveTypeRepository.findAll());
        return "leavetypes";
    }

    @RequestMapping(path = "/leavetypes/edit/{positionName}", method = RequestMethod.GET)
    public String editLeavetype(Model model, @PathVariable(value = "positionName") String positionName) {   	
    	Position p = leaveTypeRepository.findById(positionName).orElse(null);
    	System.out.println(p);
        model.addAttribute("leavetype", p);
        return "edit";
    }

    @RequestMapping(path = "/leavetypes/delete/{positionName}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable(name = "positionName") String positionName) {
    	leaveTypeRepository.delete(leaveTypeRepository.findById(positionName).orElse(null));
        return "redirect:/LeaveType/leavetypes";
    }
	
	

}
