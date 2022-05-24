package org.twitter.controller;

import io.dropwizard.validation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.twitter.dto.TweetRequestDTO;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TweetController {

    @PostMapping("/tweet")
    public Response tweet(@Validated @RequestBody TweetRequestDTO tweetRequestDTO) {
        Twitter twitter = TwitterFactory.getSingleton();
        if (twitter == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            twitter.updateStatus(tweetRequestDTO.getTweet());
        } catch (TwitterException e) {
            return Response.serverError().build();
        }
        return Response.ok("Tweet successful").build();
    }
}
