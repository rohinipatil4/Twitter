package org.twitter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.twitter.service.TimelineService;
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
    private TimelineService timelineService;

    private static Logger LOGGER = LoggerFactory.getLogger(TimelineController.class);

    @GetMapping("/timeline")
    public Response getLatestHomeTimeline() throws TwitterException {
        ResponseList<Status> timeline = timelineService.getUserHomeTimeline();
        if(timeline == null || timeline.size() == 0){
            LOGGER.debug("User timeline is null");
            return Response.noContent().build();
        }else {
            return Response.ok(timeline).build();
        }
    }

}

