package org.twitter.controller;

import org.springframework.web.bind.annotation.*;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
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

    @GetMapping("/timeline")
    public Response getLatestHomeTimeline() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
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

