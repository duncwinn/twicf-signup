package io.pivotal.thisweekincf;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

	private DateService dateService;

	@Autowired
	public HomePageController(DateService dateService) {
		this.dateService = dateService;
	}

	@RequestMapping("/")
	public ModelAndView homePage() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("today", dateService.getDate());
		modelAndView.addObject("dateFormat", DateTimeFormatter.ofPattern("d MMM uuuu"));
		return modelAndView;
	}

}
