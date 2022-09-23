package by.htp.ex.controller;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.controller.constant.RequestParameterName;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class DoDeleteNews {
	@Autowired
	private NewsService newsService;
	private static final Logger log = LogManager.getLogger(DoDeleteNews.class);

	@RequestMapping("/deleteNews")
	public String deleteNews(HttpServletRequest request, RedirectAttributes attr) {
		HttpSession session = request.getSession(false);
		String userRoleName = "admin";
		if (session == null) {
			return "redirect:/base_page";
		}
		if (!userRoleName.equals(session.getAttribute(AttributeName.USER_ROLE))) {
			return "redirect:/base_page";
		}
		String[] idNews = request.getParameterValues(RequestParameterName.ID);
		try {
			if (idNews == null) {
				return "redirect:/newsList";
			}
			newsService.delete(idNews);
			attr.addAttribute("deleteMessage", "delete ok");
			return "redirect:/newsList";
		} catch (ServiceException e) {
			log.error(e);
			return "error";
		}
	}
}
