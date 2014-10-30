package io.pivotal.thisweekincf;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class HomePageControllerIntegrationTests {

	@Autowired
	HomePageController controller;

	@Test
	public void theHomePageFeaturesTodaysDate() {
		ModelAndView modelAndView = controller.homePage();

		assertThat(modelAndView.getModel().get("today"), equalTo(LocalDate.now()));
	}

}
