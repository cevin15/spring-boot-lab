package com.youbenzi;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	private Logger logger = Logger.getLogger(HelloController.class.getName());

	@RequestMapping(path = "/hello")
	public String hello(@RequestParam(defaultValue = "World", required = false, name = "name") String name,
			Model model) {
		model.addAttribute("name", name);
		return "hello";
	}

	@Scheduled(fixedRate=3000)
	public void reportCurrentTime() {
		logger.info(new Date().toString());
	}
}
