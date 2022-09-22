package by.htp.ex.controller;

import by.htp.ex.bean.News;
import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.controller.constant.RequestParameterName;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class GoToEditNews  {
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final Logger log = LogManager.getLogger(GoToEditNews.class);


    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession(false);
    	String userRoleName = "admin";
        if(session==null) {
        	response.sendRedirect(PageName.INDEX_PAGE);
        	return;
        }
        if(!userRoleName.equals(session.getAttribute(AttributeName.USER_ROLE))) {
        	response.sendRedirect(PageName.INDEX_PAGE);
        	return;
        }
    	String id = request.getParameter(RequestParameterName.ID);
        try {
            String statusOfEdit = "active";
            News news = newsService.findById(Integer.parseInt(id));
            request.setAttribute(AttributeName.NEWS, news);
            request.setAttribute(AttributeName.EDIT_NEWS, statusOfEdit);
            session.setAttribute(AttributeName.URL, PageName.EDIT_NEWS_PAGE + id);
            request.getRequestDispatcher(PageName.BASELAYOUT_PAGE).forward(request, response);
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(PageName.ERROR_PAGE);
        }
    }
}
