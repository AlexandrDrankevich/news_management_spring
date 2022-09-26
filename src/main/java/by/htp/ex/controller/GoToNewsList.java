package by.htp.ex.controller;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.controller.constant.RequestParameterName;
import by.htp.ex.entity.News;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({ "newsCount", "pageNumber" })
public class GoToNewsList {
	@Autowired
	private NewsService newsService;
	private static final Logger log = LogManager.getLogger(GoToNewsList.class);

	@RequestMapping("/newsList")
	public String showNewsList(HttpServletRequest request, Model model,
			@RequestParam(value = "newsCount", required = false) String newsCount,
			@RequestParam(value = "pageNumber", required = false) String page,
			@SessionAttribute(value = "pageNumber", required = false) String sessionPage,
			@SessionAttribute(value = "newsCount", required = false) String sessionNewsCount) {
		List<News> newsListOnPage;
		String typeOfPresentation = "newsList";
		Integer pageNumber = getPageNumber(page, model, sessionPage);
		if (newsCount != null) {
			model.addAttribute("newsCount", newsCount);
			sessionNewsCount=newsCount;
		}
		try {
			newsListOnPage = newsService.list(pageNumber, sessionNewsCount);
			model.addAttribute(AttributeName.PAGE_COUNT, newsService.getPageCount(sessionNewsCount));
			model.addAttribute(AttributeName.NEWS, newsListOnPage);
			model.addAttribute(AttributeName.PRESENTATION, typeOfPresentation);
			// session.setAttribute(AttributeName.URL, PageName.NEWS_LIST_PAGE);
			return "baseLayout";
		} catch (ServiceException e) {
			log.error(e);
			return "error";
		}
	}

	private Integer getPageNumber(String page, Model model, String sessionPage) {
		Integer pageNumberDefault = 1;
		if (page != null) {
			model.addAttribute("pageNumber", Integer.valueOf(page));
			return Integer.valueOf(page);
		}
		if (sessionPage == null) {
			return pageNumberDefault;
		}
		return Integer.valueOf(sessionPage);
	}
}
