package by.htp.ex.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import by.htp.ex.bean.NewUserInfo;

@RequestMapping("/registration")
public class RegistrationCommand {
	
	@RequestMapping("/showForm")
	public String showForm(@ModelAttribute("newUserInfo")NewUserInfo newUserInfo, Model model) {
		System.out.println("dsdsdsd");
		model.addAttribute("reg", "reg");
			 return "baseLayout";
	}

}
