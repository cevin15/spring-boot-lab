package com.youbenzi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@RequestMapping(path = "/hello")
	public String hello(@RequestParam(defaultValue = "World", required = false, name = "name") String name,
			Model model) {
		model.addAttribute("name", name);
		return "hello";
	}

}
