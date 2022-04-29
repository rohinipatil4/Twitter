package org.twitter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.twitter.config.TwitterConfiguration;
import org.twitter.config.YamlConfig;
import org.twitter.model.TweetResponseDTO;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimelineService {

    @Autowired
    private YamlConfig config;

    private static Logger LOGGER = LoggerFactory.getLogger(TimelineService.class);

    public List<TweetResponseDTO> getUserHomeTimeline() throws TwitterException {
        ResponseList<Status> timeline = null;
        TwitterConfiguration twitterConfiguration = new TwitterConfiguration(config);
        Twitter twitter = twitterConfiguration.getInstance();
        List<TweetResponseDTO> timelineResponse = new ArrayList<>();
        if (twitter != null){
            LOGGER.info("Getting latest User timeline");
            timeline = twitter.getHomeTimeline();
            for (Status status: timeline) {
                TweetResponseDTO tweetResponseDTO = TweetResponseDTO.toPOJO(status.getText(), status.getUser().getScreenName(), status.getUser().getName(), status.getUser().getProfileImageURL(), status.getCreatedAt());
                timelineResponse.add(tweetResponseDTO);
            }
            LOGGER.info("Fetched user timeline successfully");
        }else {
            throw new TwitterException("Twitter instance not found");
        }
        return timelineResponse;
    }

    @Cacheable(value = "tweet")
    public List<String> filterTimeline(String filter) throws TwitterException {
        TwitterConfiguration twitterConfiguration = new TwitterConfiguration(config);
        Twitter twitter = twitterConfiguration.getInstance();
        ResponseList<Status> timeline = twitter.getHomeTimeline();
        List<String> statusList = timeline.stream().filter(status -> status.getText().toLowerCase().contains(filter.toLowerCase())).map(Status::getText).collect(Collectors.toList());
        Optional<List<String>> s = Optional.ofNullable(statusList);
        if(s.isPresent()){
            return statusList;
        }else {
            return null;
        }
    }
}
