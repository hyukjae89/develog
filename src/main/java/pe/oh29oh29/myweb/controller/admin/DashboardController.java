package pe.oh29oh29.myweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String dashboardView() {
		return "admin/contents/dashboard";
	}
	
}
