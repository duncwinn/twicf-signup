package io.pivotal.twicf.signup;

/**
 * Created by duncwinn on 15/12/14.
 * Used to provide info on profiles and services
 */
public class ApplicationInfo {

    private String[] profiles;
    private String[] services;

    public ApplicationInfo(String[] profiles, String[] services) {
        this.profiles = profiles;
        this.services = services;
    }

    public String[] getProfiles() {
        return profiles;
    }

    public void setProfiles(String[] profiles) {
        this.profiles = profiles;
    }

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

}
