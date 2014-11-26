package io.pivotal.thisweekincf;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.Filter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



/**
 * Created by duncwinn on 25/11/14.
 * http://spring.io/blog/2014/05/23/preview-spring-security-test-web-security
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SecurityTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

//    @Test
//    public void postWithCsrfWorks() throws Exception {
//        mvc
//                .perform(post("/").with(csrf()))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void postWithCsrfWorksWithPut() throws Exception {
//        mvc
//                .perform(put("/").with(csrf()))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void postWithNoCsrfForbidden() throws Exception {
//        mvc
//                .perform(post("/"))
//                .andExpect(status().isForbidden());
//    }

//    @Test
//    public void authenticationFailed() throws Exception {
//        mvc
//                .perform(formLogin().user("user").password("invalid"))
//                .andExpect(status().isMovedTemporarily())
//                .andExpect(redirectedUrl("/login?error"))
//                .andExpect(unauthenticated());
//    }


    @Configuration
    @EnableWebMvcSecurity
    @EnableWebMvc
    static class Config extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .inMemoryAuthentication()
                    .withUser("user").password("password").roles("USER");
        }
    }
}

