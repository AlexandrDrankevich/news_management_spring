package by.htp.ex.controller;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.controller.constant.RequestParameterName;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class DoSIgnIn  {

    private final UserService service = ServiceProvider.getInstance().getUserService();
    private static final Logger log = LogManager.getLogger(DoSIgnIn.class);


    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter(RequestParameterName.LOGIN);
        String password = request.getParameter(RequestParameterName.PASSWORD);
        String userRoleName = "guest";
        String userStatusActive = "active";
        String userStatusNotActive = "not active";
        HttpSession session=request.getSession(true);
        try {
            String role = service.signIn(login, password);
            if (!role.equals(userRoleName)) {
            	session.setAttribute(AttributeName.LOGIN, login);
            	session.setAttribute(AttributeName.USER, userStatusActive);
            	session.setAttribute(AttributeName.USER_ROLE, role);
                response.sendRedirect(PageName.NEWS_LIST_PAGE);
            } else {
            	session.setAttribute(AttributeName.USER, userStatusNotActive);
            	session.setAttribute(AttributeName.URL, PageName.BASE_PAGE);
                response.sendRedirect(PageName.BASE_PAGE + "&AuthenticationError=wrong login or password");
            }
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(PageName.INDEX_PAGE);
        }
    }
}
