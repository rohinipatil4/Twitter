package org.twitter.common;

import org.apache.commons.lang3.StringUtils;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class PostTweet {

    public static Status postTweet(Twitter twitter, String post) throws TwitterException {
        if(twitter == null){
            throw new TwitterException("Unable to initialize object");
        }
        if(StringUtils.isBlank(post)){
            throw new TwitterException("Please enter the Post content");
        }
        Status status = twitter.updateStatus(post);
        System.out.println("Successfully updated the status to [" + status.getText() + "].");
        return status;
    }
}
