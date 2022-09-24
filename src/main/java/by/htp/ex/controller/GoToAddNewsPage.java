package by.htp.ex.controller;

import by.htp.ex.bean.News;
import by.htp.ex.controller.constant.AttributeName;

import by.htp.ex.controller.constant.PageName;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoToAddNewsPage {

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
}
