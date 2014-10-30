package io.pivotal.thisweekincf;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HomePageControllerUnitTests {

	@Test
	public void theHomePageIsTheIndexTemplate() throws Exception {
		HomePageController controller = new HomePageController(new DateService());
		
		assertThat(controller.homePage().getViewName(), equalTo("index"));
	}
	
}
