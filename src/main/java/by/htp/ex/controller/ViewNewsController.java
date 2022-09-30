package by.htp.ex.controller;

import by.htp.ex.entity.News;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewNewsController {
	@Autowired
	private NewsService newsService;
	private static final String newsAttribute = "news";
	private static final String typeOfPresentation = "viewNews";
	private static final String presentationTypeAttribute = "presentation";

	@RequestMapping("/viewNews/{id}")
	public String viewNews(@PathVariable("id") String id, HttpServletRequest request) {
		try {
			News news = newsService.findById(Integer.parseInt(id));
			request.setAttribute(newsAttribute, news);
			request.setAttribute(presentationTypeAttribute, typeOfPresentation);
			return "baseLayout";
		} catch (ServiceException e) {
			return "error";
		}
	}
}
