package by.htp.ex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.controller.constant.RequestParameterName;
import by.htp.ex.entity.News;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;

@Controller
@RequestMapping("/news")
public class NewsProcessingController {
	
	@Autowired
	private NewsService newsService;
	private static final Logger log = LogManager.getLogger();
	
	@RequestMapping("/addNewsForm")
	public String showAddNewsForm(@ModelAttribute("news") News news, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String userRoleName = "admin";
		if (session == null) {
			return "redirect:/base_page";
		}
		if (!userRoleName.equals(session.getAttribute(AttributeName.USER_ROLE))) {
			return "redirect:/base_page";
		}

		String addNewsStatus = "active";
		session.setAttribute(AttributeName.URL, PageName.ADD_NEWS_PAGE);
		request.setAttribute(AttributeName.ADD_NEWS, addNewsStatus);
		return "baseLayout";
	}
	
	@RequestMapping("/saveNews")
	public String addNews(HttpServletRequest request, @ModelAttribute("news") News news, @SessionAttribute("idUser") int idUser,
			RedirectAttributes attr) {
		HttpSession session = request.getSession(false);
		String userRoleName = "admin";
		if (session == null) {
			return "redirect:/base_page";
		}
		if (!userRoleName.equals(session.getAttribute(AttributeName.USER_ROLE))) {
			return "redirect:/base_page";
		}
			try {
			news.setReporterId(idUser);
			newsService.save(news);
			attr.addAttribute("newsMessage", "News saved!");
			return "redirect:/viewNews/"+news.getIdNews();
				} catch (ServiceException e) {
			log.error(e);
			return "error";
		}
	}
	
	
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
	
	@RequestMapping("/delete")
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
