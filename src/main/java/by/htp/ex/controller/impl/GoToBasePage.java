package by.htp.ex.controller.impl;


import by.htp.ex.bean.News;
import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.controller.constant.RequestParameterName;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;

import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

@Controller
public class GoToBasePage {
   //private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
  //  private static final Logger log = LogManager.getLogger(GoToBasePage.class);
@RequestMapping("/base_page")
    public String  goToBasePage(HttpServletRequest request, Model model)  {
	
    return "baseLayout";
      // List<News> latestNews;
       // int countNews = 5;
     //  try {
        //    latestNews = newsService.latestList(countNews);
       //     checkParameter(request);
       //     request.setAttribute(AttributeName.NEWS, latestNews);

            //return PageName.BASELAYOUT_PAGE;
   //   } catch (ServiceException e) {
         //   log.error(e);
     //       return "error";
     //   }
 //   }

   // private void checkParameter(HttpServletRequest request) {
     //   if (request.getParameter(RequestParameterName.REGISTRATION_PAGE_URL) != null) {
      //      request.getSession().removeAttribute(AttributeName.URL);
    //    }
    }
}
