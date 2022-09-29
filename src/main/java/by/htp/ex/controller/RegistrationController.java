package by.htp.ex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.controller.constant.RequestParameterName;
import by.htp.ex.entity.UserInfo;
import by.htp.ex.entity.UserRole;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private UserService service;
	private static final String userInfoAttribute = "newUserInfo";
	private static final String regResultMessageAttribute = "regMessage";
	private static final String messageLoginExistAttribute = "messageLoginExist";
	private static final String registrationAttribute = "reg";
	private static final String regStatus = "active";
	private static final String regResultMessage = "Successful registration!";

	private static final Logger log = LogManager.getLogger(UserService.class);

	@RequestMapping("/showForm")
	public String showForm(@ModelAttribute(userInfoAttribute) UserInfo newUserInfo, Model model) {
		model.addAttribute(registrationAttribute, regStatus);
		return "baseLayout";
	}

	@RequestMapping("/do_registration")
	public String doRegistration(@ModelAttribute(userInfoAttribute) UserInfo newUserInfo, RedirectAttributes attr) {

		String hashPassword = BCrypt.hashpw(newUserInfo.getPassword(), BCrypt.gensalt());
		newUserInfo.setPassword(hashPassword);
		try {
			UserRole defaultUserRole = new UserRole();
			defaultUserRole.setId(2);
			newUserInfo.setUserRole(defaultUserRole);
			boolean result = service.registration(newUserInfo);
			if (result) {
				attr.addAttribute(regResultMessageAttribute, regResultMessage);
				return "redirect:/base_page";
			} else {
				attr.addAttribute(messageLoginExistAttribute, newUserInfo.getLogin());
				return "redirect:/registration/showForm";

			}
		} catch (ServiceException e) {
			log.error(e);
			return "redirect:/base_page";
		}
	}
}
