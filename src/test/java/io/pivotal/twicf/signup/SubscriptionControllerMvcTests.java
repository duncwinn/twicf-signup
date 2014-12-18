package io.pivotal.twicf.signup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jvnet.mock_javamail.Mailbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by duncwinn on 12/11/2014.
 */
//RunWith allows an entry point into JUnit to hook into the test cycle, creating a test framework / sandbox env to run the tests.
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SubscriptionControllerMvcTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        Mailbox.clearAll();
    }

    @Test
    public void subscriptionPageIsAccessible() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());


    }

    @Test
    public void expectedFormElementsArePresent() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(xpath("//input[@id='emailAddress']").nodeCount(1))
                .andExpect(xpath("//button[@id='subscribe']").nodeCount(1));
    }

    @Test
    public void postCreatesANewSubscription() throws Exception {
        assertEquals(0, this.subscriptionRepository.findAll().size());
        assertEquals(0, Mailbox.get("testing@example.com").size());

        mockMvc.perform(post("/").param("emailAddress", "testing@example.com"))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/confirmation"));

        assertEquals(1, this.subscriptionRepository.findAll().size());
        assertNotNull(this.subscriptionRepository.findByEmailAddress("testing@example.com"));
        assertEquals(1, Mailbox.get("testing@example.com").size());
    }

    @Test
    public void displaysConfirmation() throws Exception {
        mockMvc.perform(get("/confirmation"))
                .andExpect(status().isOk())
                .andExpect(xpath("//h1[@id='message']").string("Thanks for subscribing!"));
    }
}
