package org.twitter.config;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfiguration {

    private final YamlConfig config;
    public static Twitter twitter;

    public TwitterConfiguration(YamlConfig config) {
        this.config = config;
    }

    public void setConfig(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(config.getoAuthConsumerKey())
                .setOAuthConsumerSecret(config.getoAuthConsumerSecret())
                .setOAuthAccessToken(config.getoAuthAccessToken())
                .setOAuthAccessTokenSecret(config.getoAuthAccessTokenSecret());
        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        twitter = twitterFactory.getInstance();
    }

    public Twitter getInstance(){
        if(null == twitter){
            setConfig();
        }
        return twitter;
    }
}
