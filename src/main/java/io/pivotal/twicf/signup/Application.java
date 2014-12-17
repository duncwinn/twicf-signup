package io.pivotal.twicf.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.Arrays;

//You don't strictly need @Configuration as boot will do this for you but it's a good safeguard
//@ComponentScan searched and creates beans from this package and it's children


//@ComponentScan
//@Configuration
//@EnableAutoConfiguration
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

    @Configuration
    @ServiceScan
    @Profile("cloud")
    static class CloudConfiguration {
    }

//    //Once you define the above 4 lines for "CloudConfiguration" you can access any injected services.
//    //For Example
//    @Bean
//    Object doSomethingWithADataSource(DataSource dataSource) {
//        // TOOD: With DataSource
//    }

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
                    .antMatchers("/", "/confirmation", "/reconfirmation","/nomailconfirmation","/webjars/**", "/css/**","/cloud-foundry-logo.png").permitAll()
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
