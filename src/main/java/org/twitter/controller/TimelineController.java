package org.twitter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.twitter.model.TweetResponseDTO;
import org.twitter.service.TimelineService;
import twitter4j.TwitterException;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/twitter/api/1.0")
@Produces(MediaType.APPLICATION_JSON)
public class TimelineController {

    @Autowired
    private TimelineService timelineService;

    private static Logger LOGGER = LoggerFactory.getLogger(TimelineController.class);

    @GetMapping("/timeline")
    public Response getLatestHomeTimeline() throws TwitterException {
        List<TweetResponseDTO> timelineResponse = timelineService.getUserHomeTimeline();
        if(timelineResponse == null || timelineResponse.size() == 0){
            LOGGER.debug("User timeline is null");
            return Response.noContent().build();
        }else {
            return Response.ok(timelineResponse).build();
        }
    }

    @GetMapping("/timeline/filter")
    public Response filterTimeline(@RequestParam String filter) throws TwitterException {
        List<String> timeline = timelineService.filterTimeline(filter);
        if(null == timeline || timeline.size() == 0){
            return Response.noContent().build();
        }
        return Response.ok(timeline).build();
    }

}

