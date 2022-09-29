package by.htp.ex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import by.htp.ex.entity.UserInfo;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;


@Controller
@SessionAttributes({"user", "role","idUser"})
public class AuthorizationController {
	@Autowired
	private UserService service;
	private static final Logger log = LogManager.getLogger();
	

	@RequestMapping("/signIn")
	public String signIn(@RequestParam("login") String login, @RequestParam("password") String password,
			 RedirectAttributes attr, Model model)
			throws ServletException, IOException {

		String userStatusActive = "active";
		String userStatusNotActive = "not active";
		try {
			 UserInfo user = service.signIn(login, password);
			if (user!=null) {
				model.addAttribute("idUser", user.getId());
				model.addAttribute("user", userStatusActive);
				model.addAttribute("role", user.getUserRole().getRole());
				return "redirect:/newsList";
				
			} else {
				model.addAttribute("user", userStatusNotActive);
				attr.addAttribute("AuthenticationError", "wrong login or password");
				return "redirect:/base_page";
			}
		} catch (ServiceException e) {
			log.error(e);
			return "redirect:/base_page";
		}
	}
	
	@RequestMapping("/signOut")
	public String signOut(HttpSession session,  Model model, SessionStatus status ) {
	//	String userStatusNotActive = "not active";
		//model.addAttribute("user", userStatusNotActive);
		 status.setComplete();
		session.invalidate();
		return "redirect:/base_page";
	}
}
