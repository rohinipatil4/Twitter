package org.twitter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YamlConfig {
    private String name;
    private String environment;
    private boolean enabled;

    @Value("${oauth.consumerKey}")
    private String oAuthConsumerKey;

    @Value("${oauth.consumerSecret}")
    private String oAuthConsumerSecret;

    @Value("${oauth.accessToken}")
    private String oAuthAccessToken;

    @Value("${oauth.accessTokenSecret}")
    private String oAuthAccessTokenSecret;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getoAuthConsumerKey() {
        return oAuthConsumerKey;
    }

    public void setoAuthConsumerKey(String oAuthConsumerKey) {
        this.oAuthConsumerKey = oAuthConsumerKey;
    }

    public String getoAuthConsumerSecret() {
        return oAuthConsumerSecret;
    }

    public void setoAuthConsumerSecret(String oAuthConsumerSecret) {
        this.oAuthConsumerSecret = oAuthConsumerSecret;
    }

    public String getoAuthAccessToken() {
        return oAuthAccessToken;
    }

    public void setoAuthAccessToken(String oAuthAccessToken) {
        this.oAuthAccessToken = oAuthAccessToken;
    }

    public String getoAuthAccessTokenSecret() {
        return oAuthAccessTokenSecret;
    }

    public void setoAuthAccessTokenSecret(String oAuthAccessTokenSecret) {
        this.oAuthAccessTokenSecret = oAuthAccessTokenSecret;
    }
}
