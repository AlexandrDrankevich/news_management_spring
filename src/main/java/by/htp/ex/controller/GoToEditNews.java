package by.htp.ex.controller;

import by.htp.ex.bean.News;
import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.constant.PageName;

import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoToEditNews {
	@Autowired
	private NewsService newsService;
	private static final Logger log = LogManager.getLogger(GoToEditNews.class);

	@RequestMapping("/editNews/{id}")
	public String editNews(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		String userRoleName = "admin";
		if (session == null) {
			return "redirect:/base_page";
		}
		if (!userRoleName.equals(session.getAttribute(AttributeName.USER_ROLE))) {
			return "redirect:/base_page";
		}

		try {
			String statusOfEdit = "active";
			News news = newsService.findById(Integer.parseInt(id));
			request.setAttribute(AttributeName.NEWS, news);
			request.setAttribute(AttributeName.EDIT_NEWS, statusOfEdit);
			session.setAttribute(AttributeName.URL, PageName.EDIT_NEWS_PAGE + id);
			return "baseLayout";
		} catch (ServiceException e) {
			log.error(e);
			return "error";
		}
	}
}
