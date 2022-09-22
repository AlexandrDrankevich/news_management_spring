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
import java.util.ArrayList;
import java.util.List;

public class GoToNewsList  {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final Logger log = LogManager.getLogger(GoToNewsList.class);


	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newsListOnPage;
		String typeOfPresentation = "newsList";
		Integer pageNumber = getPageNumber(request);
		String newsCount=request.getParameter(RequestParameterName.NEWS_COUNT);
		HttpSession session=request.getSession(true);
		if(newsCount!=null) {
			session.setAttribute(AttributeName.NEWS_COUNT,newsCount);
		}
		try {
			newsListOnPage = newsService.list(pageNumber,(String)request.getSession(true).getAttribute("newsCount"));
			request.setAttribute(AttributeName.PAGE_COUNT, newsService.getPageCount());
			request.setAttribute(AttributeName.NEWS, newsListOnPage);
			request.setAttribute(AttributeName.PRESENTATION, typeOfPresentation);
			session.setAttribute(AttributeName.URL, PageName.NEWS_LIST_PAGE);
			request.getRequestDispatcher(PageName.BASELAYOUT_PAGE).forward(request, response);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(PageName.ERROR_PAGE);
		}
	}

	private Integer getPageNumber(HttpServletRequest request) {
		String page = request.getParameter(RequestParameterName.PAGE_NUMBER);
		if (page != null) {
			request.getSession().setAttribute(AttributeName.PAGE_NUMBER, Integer.valueOf(page));
		}
		Integer pageNumber = (Integer) request.getSession(true).getAttribute(AttributeName.PAGE_NUMBER);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		return pageNumber;
	}
}
