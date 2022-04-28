package org.twitter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.twitter.config.TwitterConfiguration;
import org.twitter.config.YamlConfig;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.ResponseList;
import twitter4j.Status;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TimelineController {

    @Autowired
    private YamlConfig config;

    private static Logger LOGGER = LoggerFactory.getLogger(TimelineController.class);

    @GetMapping("/timeline")
    public Response getLatestHomeTimeline() throws TwitterException {
        TwitterConfiguration twitterConfiguration = new TwitterConfiguration(config);
        Twitter twitter = twitterConfiguration.getInstance();
        if (twitter != null){
            LOGGER.info("Getting latest User timeline");
            ResponseList<Status> timeline = twitter.getHomeTimeline();
            if(timeline == null || timeline.size() == 0){
                LOGGER.debug("User timeline is null");
                return Response.noContent().build();
            }
            LOGGER.info("Fetched user timeline successfully");
            return Response.ok(timeline).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}

