package by.htp.ex.controller.impl;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.controller.constant.RequestParameterName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class DoChangeLocal  {


    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession(true);
        String local = request.getParameter(RequestParameterName.LOCAL);
        String url = (String) session.getAttribute(AttributeName.URL);
        if (url == null) {
            url = PageName.BASE_PAGE;
        }
        session.setAttribute(RequestParameterName.LOCAL, local);
        response.sendRedirect(url);
    }
}
