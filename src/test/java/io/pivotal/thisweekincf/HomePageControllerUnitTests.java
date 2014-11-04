package io.pivotal.thisweekincf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

// Unit test: I'm testing this specific function and nothing else
// This test creates the home page controller object 
// Unit tests are low level, easy to understand and good for specific detail
public class HomePageControllerUnitTests {

    private final DateService dateService = mock(DateService.class);

	@Test
	public void theHomePageIsTheIndexTemplate() throws Exception {
        when(this.dateService.getDate()).thenReturn(LocalDate.MIN);

		HomePageController controller = new HomePageController(this.dateService);

        ModelAndView modelAndView = controller.homePage();

        assertEquals("index", modelAndView.getViewName());
        assertEquals(LocalDate.MIN, modelAndView.getModel().get("today"));
	}

}
