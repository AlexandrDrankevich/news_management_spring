package by.htp.ex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import by.htp.ex.entity.UserInfo;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;

@Controller
public class AuthorizationController {
	@Autowired
	private UserService service;
	private static final String userStatusActive = "active";
	private static final String userStatusNotActive = "not active";
	private static final String userIdAttribute="idUser";
	private static final String userStatusAttribute="user";
	private static final String userRoleAttribute="role";
	private static final String authenticationErrorMessageAttribute="AuthenticationError";
	private static final String authenticationErrorMessage="wrong login or password";
	private static final Logger log = LogManager.getLogger();

	@RequestMapping("/signIn")
	public String signIn(@RequestParam("login") String login, @RequestParam("password") String password,
			RedirectAttributes attr, HttpSession session) throws ServletException, IOException {
		try {
			UserInfo user = service.signIn(login, password);
			if (user != null) {
				session.setAttribute(userIdAttribute, user.getId());
				session.setAttribute(userStatusAttribute, userStatusActive);
				session.setAttribute(userRoleAttribute, user.getUserRole().getRole());
				return "redirect:/newsList";

			} else {
				session.setAttribute(userStatusAttribute, userStatusNotActive);
				attr.addAttribute(authenticationErrorMessageAttribute, authenticationErrorMessage);
				return "redirect:/base_page";
			}
		} catch (ServiceException e) {
			log.error(e);
			return "redirect:/base_page";
		}
	}

	@RequestMapping("/signOut")
	public String signOut(HttpSession session) {
		session.invalidate();
		return "redirect:/base_page";
	}
}
