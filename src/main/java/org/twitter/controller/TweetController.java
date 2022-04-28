package org.twitter.controller;

import io.dropwizard.validation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.twitter.dto.TweetRequestDTO;
import org.twitter.service.TweetService;
import twitter4j.TwitterException;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @PostMapping("/tweet")
    public Response tweet(@Validated @RequestBody TweetRequestDTO tweetRequestDTO) throws TwitterException {
        tweetService.publishTweet(tweetRequestDTO);
        return Response.ok("Tweet successful").build();
    }
}
