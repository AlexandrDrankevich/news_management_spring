package by.htp.ex.controller;

import by.htp.ex.bean.News;
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

public class DoAddNews  {
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final Logger log = LogManager.getLogger(DoAddNews.class);


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
    	String title = request.getParameter(RequestParameterName.TITLE);
        String briefNews = request.getParameter(RequestParameterName.BRIEF_NEWS);
        String content = request.getParameter(RequestParameterName.CONTENT);
        String login = (String) session.getAttribute(RequestParameterName.LOGIN);
        String newsDate = request.getParameter(RequestParameterName.DATE);
        
        News news = new News(title, briefNews, content, newsDate);
        try {
            newsService.save(news, login);
            response.sendRedirect(PageName.VIEW_NEWS + news.getIdNews() + "&newsMessage=News saved!");

        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(PageName.ERROR_PAGE);
        }

    }

}
