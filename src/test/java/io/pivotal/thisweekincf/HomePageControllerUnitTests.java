package io.pivotal.thisweekincf;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

// Unit test: I'm testing this specific function and nothing else
// This test creates the home page controller object 
// Unit tests are low level, easy to understand and good for specific detail
public class HomePageControllerUnitTests {

	@Test
	public void theHomePageIsTheIndexTemplate() throws Exception {
		HomePageController controller = new HomePageController(new DateService());

		assertThat(controller.homePage().getViewName(), equalTo("index"));
	}

}
