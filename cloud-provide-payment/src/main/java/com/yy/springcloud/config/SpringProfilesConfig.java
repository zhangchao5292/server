package com.yy.springcloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * SpringProfilesConfig
 *
 * @author tianyi.wei
 */
@Configuration
public class SpringProfilesConfig {

    @Value("${spring.profiles:prod}")
    private String profiles;

    public String getProfiles() {
        return profiles;
    }

    public void setProfiles(String profiles) {
        this.profiles = profiles;
    }
}
