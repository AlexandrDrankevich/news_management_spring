package by.htp.ex.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import by.htp.ex.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsAddUpdateController {
	
	@Autowired
	private NewsService newsService;
	private static final Logger log = LogManager.getLogger(DoAddNews.class);

}
