package by.htp.ex.controller.impl;

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

public class GoToViewNews  {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final Logger log = LogManager.getLogger(GoToViewNews.class);


	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(PageName.INDEX_PAGE);
			return;
		}
		String id = request.getParameter(RequestParameterName.ID);
		String typeOfPresentation = "viewNews";
		try {
			News news = newsService.findById(Integer.parseInt(id));
			request.setAttribute(AttributeName.NEWS, news);
			request.setAttribute(AttributeName.PRESENTATION, typeOfPresentation);
			session.setAttribute(AttributeName.URL, PageName.VIEW_NEWS + id);
			request.getRequestDispatcher(PageName.BASELAYOUT_PAGE).forward(request, response);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(PageName.ERROR_PAGE);
		}
	}
}
