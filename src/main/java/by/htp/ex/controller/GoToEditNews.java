package by.htp.ex.controller;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.entity.News;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GoToEditNews {
	@Autowired
	private NewsService newsService;
	private static final Logger log = LogManager.getLogger(GoToEditNews.class);

	@RequestMapping("/editNews/{id}")
	public String showEditNewsForm(@PathVariable("id") String id, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		String userRoleName = "admin";
		if (session == null) {
			return "redirect:/base_page";
		}
		if (!userRoleName.equals(session.getAttribute(AttributeName.USER_ROLE))) {
			return "redirect:/base_page";
		}

		try {
		    News news = newsService.findById(Integer.parseInt(id));
			model.addAttribute("news", news);
			String editNewsStatus = "active";
			request.setAttribute(AttributeName.ADD_NEWS, editNewsStatus);
			model.addAttribute("editView", editNewsStatus);
            //session.setAttribute(AttributeName.URL, PageName.EDIT_NEWS_PAGE + id);
			return "baseLayout";
		} catch (ServiceException e) {
			log.error(e);
			return "error";
		}
	}
}
