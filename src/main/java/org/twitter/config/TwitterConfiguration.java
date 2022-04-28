package org.twitter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfiguration {

    private final YamlConfig config;
    public static Twitter twitter;
    private static Logger LOGGER = LoggerFactory.getLogger(TwitterConfiguration.class);

    public TwitterConfiguration(YamlConfig config) {
        this.config = config;
    }

    public void setConfig(){
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(config.getoAuthConsumerKey())
                    .setOAuthConsumerSecret(config.getoAuthConsumerSecret())
                    .setOAuthAccessToken(config.getoAuthAccessToken())
                    .setOAuthAccessTokenSecret(config.getoAuthAccessTokenSecret());
            TwitterFactory twitterFactory = new TwitterFactory(cb.build());
            twitter = twitterFactory.getInstance();
        }catch (Exception e){
            LOGGER.error("Unable to create twitter instance");
        }
    }

    public Twitter getInstance(){
        if(null == twitter){
            setConfig();
        }
        return twitter;
    }
}
