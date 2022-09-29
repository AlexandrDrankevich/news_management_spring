package by.htp.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPageController {
	@RequestMapping("/")
	public String showForm(Model theModel) {
		return "redirect:/base_page";
	}
}
