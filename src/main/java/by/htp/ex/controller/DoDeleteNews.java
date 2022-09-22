package by.htp.ex.controller;

import by.htp.ex.controller.Command;
import by.htp.ex.controller.constant.AttributeName;
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

public class DoDeleteNews  {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final Logger log = LogManager.getLogger(DoDeleteNews.class);


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
		String[] idNews = request.getParameterValues(RequestParameterName.ID);
		try {
			if (idNews == null) {
				response.sendRedirect(PageName.NEWS_LIST_PAGE);
				return;
			}
			newsService.delete(idNews);
			response.sendRedirect(PageName.NEWS_LIST_PAGE + "&deleteMessage=delete ok");
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(PageName.ERROR_PAGE);
		}
	}
}
