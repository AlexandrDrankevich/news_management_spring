package by.htp.ex.controller;

import by.htp.ex.bean.NewUserInfo;
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
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class DoRegistration {
	@Autowired
	private UserService service;
	private static final Logger log = LogManager.getLogger(UserService.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter(RequestParameterName.NAME);
		String surname = request.getParameter(RequestParameterName.SURNAME);
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		String birthday = request.getParameter(RequestParameterName.BIRTHDAY);
		String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		HttpSession session = request.getSession(true);

		NewUserInfo user = new NewUserInfo(name, surname, login, hashPassword, birthday);
		try {
			boolean result = service.registration(user);
			if (result) {
				session.setAttribute(AttributeName.URL, PageName.BASE_PAGE);
				response.sendRedirect(PageName.BASE_PAGE + "&regMessage=Successful registration!");
			} else {
				session.setAttribute(AttributeName.URL, PageName.BASE_PAGE_WITH_REG_PARAMETER);
				response.sendRedirect(PageName.BASE_PAGE_WITH_REG_PARAMETER + "&messageLoginExist="
						+ request.getParameter(RequestParameterName.LOGIN));
			}
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(PageName.INDEX_PAGE);
		}
	}
}
