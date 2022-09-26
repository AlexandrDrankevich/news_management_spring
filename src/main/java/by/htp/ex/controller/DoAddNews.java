package by.htp.ex.controller;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.controller.constant.RequestParameterName;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DoAddNews {
	@Autowired
	private NewsService newsService;
	private static final Logger log = LogManager.getLogger(DoAddNews.class);

	@RequestMapping("/addNews")
	public String addNews(HttpServletRequest request, @ModelAttribute("news") News news, @SessionAttribute("login") String login,
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
			newsService.save(news, login);
			attr.addAttribute("newsMessage", "News saved!");
			return "redirect:/viewNews/"+news.getIdNews();
				} catch (ServiceException e) {
			log.error(e);
			return "error";
		}
	}
}
