package org.twitter.controller;

import io.dropwizard.validation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.twitter.config.TwitterConfiguration;
import org.twitter.config.YamlConfig;
import org.twitter.dto.TweetRequestDTO;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TweetController {

    @Autowired
    private YamlConfig config;

    private static Logger LOGGER = LoggerFactory.getLogger(TweetController.class);

    @PostMapping("/tweet")
    public Response tweet(@Validated @RequestBody TweetRequestDTO tweetRequestDTO) {
        TwitterConfiguration twitterConfiguration = new TwitterConfiguration(config);
        Twitter twitter = twitterConfiguration.getInstance();
        if (twitter != null){
            try {
                twitter.updateStatus(tweetRequestDTO.getTweet());
            } catch (TwitterException e) {
                LOGGER.error("Tweet unsuccessful");
                return Response.serverError().build();
            }
            LOGGER.info("Tweet successfully posted");
            return Response.ok("Tweet successful").build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
