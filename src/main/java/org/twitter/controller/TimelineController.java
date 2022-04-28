package org.twitter.controller;

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

    @GetMapping("/timeline")
    public Response getLatestHomeTimeline() throws TwitterException {
        TwitterConfiguration twitterConfiguration = new TwitterConfiguration(config);
        Twitter twitter = twitterConfiguration.getInstance();
        if (twitter != null){
            ResponseList<Status> timeline = twitter.getHomeTimeline();
            if(timeline == null || timeline.size() == 0){
                return Response.noContent().build();
            }
            return Response.ok(timeline).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}

