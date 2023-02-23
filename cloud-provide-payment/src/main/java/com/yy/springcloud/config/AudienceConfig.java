package com.yy.springcloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AudienceConfig
 *
 * @author tianyi.wei
 */
@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class AudienceConfig {
    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;
}
