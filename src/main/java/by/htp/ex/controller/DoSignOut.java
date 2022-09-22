package by.htp.ex.controller;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.constant.PageName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class DoSignOut  {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userStatusNotActive = "not active";
		HttpSession session = request.getSession();
		session.setAttribute(AttributeName.USER, userStatusNotActive);
		session.setAttribute(AttributeName.URL, PageName.BASE_PAGE);
		session.removeAttribute(AttributeName.NEWS_COUNT);
		response.sendRedirect(PageName.INDEX_PAGE);
	}
}
