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
public class NewsListController {
	@Autowired
	private NewsService newsService;
	private static final String newsOnPageAttribute = "news";
	private static final String presentationTypeAttribute = "presentation";
	private static final String newsCountParam = "newsCount";
	private static final String pageNumberParam = "pageNumber";
	private static final String pageCountAttribute = "PageCount";
	private static final String typeOfPresentation = "newsList";
	private static final String sessionNewsCounAttribute = "newsCount";
	private static final String sessionPageAttribute = "pageNumber";

	private static final Logger log = LogManager.getLogger(NewsListController.class);

	@RequestMapping("/newsList")
	public String showNewsList(HttpServletRequest request, Model model,
			@RequestParam(value = newsCountParam, required = false) String newsCount,
			@RequestParam(value = pageNumberParam, required = false) String page,
			@SessionAttribute(value = sessionPageAttribute, required = false) String sessionPage,
			@SessionAttribute(value = sessionNewsCounAttribute, required = false) String sessionNewsCount) {
		List<News> newsListOnPage;
		Integer pageNumber = getPageNumber(page, model, sessionPage);
		if (newsCount != null) {
			model.addAttribute(sessionNewsCounAttribute, newsCount);
			sessionNewsCount = newsCount;
		}
		try {
			newsListOnPage = newsService.list(pageNumber, sessionNewsCount);
			model.addAttribute(pageCountAttribute, newsService.getPageCount(sessionNewsCount));
			model.addAttribute(newsOnPageAttribute, newsListOnPage);
			model.addAttribute(presentationTypeAttribute, typeOfPresentation);
			return "baseLayout";
		} catch (ServiceException e) {
			log.error(e);
			return "error";
		}
	}

	private Integer getPageNumber(String page, Model model, String sessionPage) {
		Integer pageNumberDefault = 1;
		if (page != null) {
			model.addAttribute(sessionPageAttribute, Integer.valueOf(page));
			return Integer.valueOf(page);
		}
		if (sessionPage == null) {
			return pageNumberDefault;
		}
		return Integer.valueOf(sessionPage);
	}
}