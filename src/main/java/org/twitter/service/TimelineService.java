package org.twitter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.twitter.config.TwitterConfiguration;
import org.twitter.config.YamlConfig;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Service
public class TimelineService {

    @Autowired
    private YamlConfig config;

    private static Logger LOGGER = LoggerFactory.getLogger(TimelineService.class);

    public ResponseList getUserHomeTimeline() throws TwitterException {
        ResponseList<Status> timeline = null;
        TwitterConfiguration twitterConfiguration = new TwitterConfiguration(config);
        Twitter twitter = twitterConfiguration.getInstance();
        if (twitter != null){
            LOGGER.info("Getting latest User timeline");
            timeline = twitter.getHomeTimeline();
            LOGGER.info("Fetched user timeline successfully");
        }else {
            throw new TwitterException("Twitter instance not found");
        }
        return timeline;
    }
}
