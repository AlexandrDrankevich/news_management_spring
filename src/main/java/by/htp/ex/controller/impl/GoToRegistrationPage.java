package by.htp.ex.controller.impl;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.constant.PageName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToRegistrationPage  {


	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute(AttributeName.URL, PageName.BASE_PAGE_WITH_REG_PARAMETER);
		response.sendRedirect(PageName.BASE_PAGE_WITH_REG_PARAMETER);
	}
}
