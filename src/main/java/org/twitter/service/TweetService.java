package org.twitter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twitter.config.TwitterConfiguration;
import org.twitter.config.YamlConfig;
import org.twitter.dto.TweetRequestDTO;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Service
public class TweetService {

    @Autowired
    private YamlConfig config;

    private static Logger LOGGER = LoggerFactory.getLogger(TweetService.class);

    public void publishTweet(TweetRequestDTO tweetRequestDTO) throws TwitterException {
        TwitterConfiguration twitterConfiguration = new TwitterConfiguration(config);
        Twitter twitter = twitterConfiguration.getInstance();
        if (twitter != null){
            try {
                twitter.updateStatus(tweetRequestDTO.getTweet());
            } catch (TwitterException e) {
                LOGGER.error("Tweet unsuccessful");
                throw new TwitterException("Error while posting a tweet");
            }
            LOGGER.info("Tweet successfully posted");
        }else {
            throw new TwitterException("Internal Server Error");
        }
    }
}
