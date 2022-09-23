package by.htp.ex.controller;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.controller.constant.RequestParameterName;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@SessionAttributes({"user", "role","login"})
public class DoSIgnIn {
	@Autowired
	private UserService service;
	private static final Logger log = LogManager.getLogger(DoSIgnIn.class);

	@RequestMapping("/signIn")
	public String signIn(@RequestParam("login") String login, @RequestParam("password") String password,
			 RedirectAttributes attr, Model model)
			throws ServletException, IOException {

		String userRoleName = "guest";
		String userStatusActive = "active";
		String userStatusNotActive = "not active";
		try {
			String role = service.signIn(login, password);
			if (!role.equals(userRoleName)) {
				model.addAttribute("login", login);
				model.addAttribute("user", userStatusActive);
				model.addAttribute("role", role);
				return "redirect:/newsList";
				// response.sendRedirect(PageName.NEWS_LIST_PAGE);
			} else {
				model.addAttribute("user", userStatusActive);
				// session.setAttribute(AttributeName.URL, PageName.BASE_PAGE);
				attr.addAttribute("AuthenticationError", "wrong login or password");
				return "redirect:/base_page";
			}
		} catch (ServiceException e) {
			log.error(e);
			return "redirect:/base_page";
		}
	}
}
