package io.pivotal.thisweekincf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

//You don't strictly need @Configuration as boot will do this for you but it's a good safeguard
//@ComponentScan searched and creates beans from this package and it's children


@ComponentScan
@Configuration
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
        //Used for determining profile
        ApplicationContext appctx;

        appctx = SpringApplication.run(Application.class, args);

        System.out.println("Current Profile: " + appctx.getBean(Environment.class).getActiveProfiles()[0].toString());
	}

}
