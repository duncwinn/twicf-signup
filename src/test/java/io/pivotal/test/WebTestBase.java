package io.pivotal.test;

import io.pivotal.thisweekincf.Application;

import org.fluentlenium.adapter.FluentTest;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public abstract class WebTestBase extends FluentTest {

	@Autowired
	private WebApplicationContext context;

	public WebTestBase() {
		super();
	}

	// WebDriver is a headless browser (htmlunit)
	@Override
	public WebDriver getDefaultDriver() {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		return new HtmlUnitDriver() {
			@Override
			protected WebClient newWebClient(BrowserVersion version) {
				WebClient webClient = new WebClient(version);
				webClient.setWebConnection(new MockMvcWebConnection(mockMvc));
				return webClient;
			}
		};
	}

	@Override
	public String getDefaultBaseUrl() {
		return "http://localhost/app";
	}

}