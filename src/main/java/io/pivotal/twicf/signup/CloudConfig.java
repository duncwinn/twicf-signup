package io.pivotal.twicf.signup;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by duncwinn on 23/12/14.
 */
@Configuration
@Profile({"cloud"})
public class CloudConfig extends AbstractCloudConfig {

}