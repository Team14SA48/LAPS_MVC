package sg.edu.nus.lapsystem.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.nus.lapsystem.model.Employee;
import sg.edu.nus.lapsystem.service.EmployeeService;

@Controller
public class LoginController {

	@Autowired
	private EmployeeService es;

	// need to show error message
	@RequestMapping(path = { "/index", "/" })
	public String login(Model model) {
		model.addAttribute("LoginForm", new Employee());
		return "login";
	}

	@RequestMapping(path = "/login")
	public String login(@ModelAttribute Employee loginForm, HttpServletResponse res, Model model) {
		int userId = loginForm.getId();
		Employee emp = es.findById(userId);
		String userPassword = loginForm.getPassword();

		// this maybe no need
		model.addAttribute("details", emp);

		if (emp != null && userPassword.equals(emp.getPassword())) {
			Cookie cookie = new Cookie("userId", String.valueOf(emp.getId()));
			cookie.setMaxAge(3600);	
			res.addCookie(cookie);
			return "redirect:/menu";
		} else
			return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletResponse res) {
		Cookie cookie = new Cookie("userId","");
		cookie.setMaxAge(0);
		res.addCookie(cookie);
		return "redirect:/";
	}
}
