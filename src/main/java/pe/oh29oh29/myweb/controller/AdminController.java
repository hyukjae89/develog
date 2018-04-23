package pe.oh29oh29.myweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.oh29oh29.myweb.common.Constants;
import pe.oh29oh29.myweb.model.Member;

@Controller
public class AdminController {
	
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String adminMainView(HttpServletRequest httpRequest) throws Exception {
		
		Object sessionAttr = httpRequest.getSession().getAttribute(Constants.SESSION_MEMBER);
		
		if (sessionAttr == null) {
			throw new Exception();
		} 
		
		Member member = (Member) sessionAttr;
		
		if (member.getIsAdmin() == Constants.FALSE) {
			throw new Exception();
		}
		
		return "./admin/main";
	}
}
