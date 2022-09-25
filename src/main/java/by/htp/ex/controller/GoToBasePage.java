package by.htp.ex.controller;

import by.htp.ex.bean.News;
import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.constant.RequestParameterName;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
public class GoToBasePage {
	@Autowired
	private NewsService newsService;
	private static final Logger log = LogManager.getLogger(GoToBasePage.class);

	@RequestMapping("/base_page")
	public String goToBasePage(HttpServletRequest request, Model model) {

		List<News> latestNews;
		int countNews = 5;
		try {
			latestNews = newsService.latestList(countNews);
			// checkParameter(request);
			model.addAttribute(AttributeName.NEWS, latestNews);
			return "baseLayout";

		} catch (ServiceException e) {
			log.error(e);
			return "error";
		}
	}

	private void checkParameter(HttpServletRequest request) {
		if (request.getParameter(RequestParameterName.REGISTRATION_PAGE_URL) != null) {
			request.getSession().removeAttribute(AttributeName.URL);
		}
	}
}