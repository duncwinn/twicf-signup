package io.pivotal.twicf.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.Arrays;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    @Value("${spring.profiles.active:there,are,no,profiles,specified}")
    private volatile String[] profiles;

    @PostConstruct
    void printProfiles() {
        System.out.println("Current Profiles: " + Arrays.toString(this.profiles));
    }

//    @Configuration
//    @ServiceScan
//    @Profile("cloud")
//    static class CloudConfiguration extends AbstractCloudConfig {
//
//    }

    //CloudConfiguration needed for Spring to provide the Cloud class to AdminController for getting services
    //Replaces the need for extending AbstractCloudConfig
    //The cloud bean is a post processor of your spring config and will look for anything like DB driver to inject the
    // config read from cf. Adding @ServiceScan means that spring cloud adds a new bean of type BeanPostProcessor
    // which is hooking into config after first pass reading of your spring config and is used to amend things like
    // properties (username/password for drivers) this is what a cloud config bean does.

    //ProTip Git Search: in:java \@ServiceScan
    @Configuration
    @ServiceScan
    @Profile("cloud")
    static class CloudConfiguration {
    }

//    //Once you define the above 4 lines for "CloudConfiguration" you can access any injected services. e.g.:
//    @Bean
//    Object doSomethingWithADataSource(DataSource dataSource) { TOOD: With DataSource }

    //This section is a lightweight replacement for WebSecurityConfig
    //Needs some additional refactoring as we should not have to include webjars and css - boot should take care of that
    @Configuration
    static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            //If IDEA formatting was on we need the below to stop it getting reformatted
            // @formatter:off
            http
                .authorizeRequests()
                    .antMatchers("/", "/confirmation", "/reconfirmation", "/nomailconfirmation",
                            "/unsubscribe", "/unsubscribed", "/nosubscriber", "/nomailunsubscribed",
                            "/webjars/**", "/css/**","/cloud-foundry-logo.png").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
            // @formatter:on
        }
    }


}
