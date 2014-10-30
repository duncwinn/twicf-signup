package io.pivotal.thisweekincf;

import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.Assert.assertThat;
import io.pivotal.test.WebTestBase;

import org.junit.Test;

public class HomePageControllerWebTests extends WebTestBase {

	@Test
	public void theHomePageFormatsTheDateSensibly() throws Exception {
		goTo("/");
		
		assertThat(find("#today").getText(), matchesPattern("\\d{1,2} \\w{3} \\d{4}"));
	}
}
