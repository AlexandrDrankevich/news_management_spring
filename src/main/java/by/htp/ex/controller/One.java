package by.htp.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class One {
	@RequestMapping("/reg")
	public String showRegForm() {
		System.out.println("fdfdfdfdf");
		return "error";
	}

}
