package org.twitter.common;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.List;

public class Timeline {

    public static void getHomeTimeLine(Twitter twitter, Integer maxCount) throws TwitterException {
        // Timelines
        List<Status> statuses = twitter.getHomeTimeline();

        System.out.println("Showing home timeline.");
        Integer timelineCount = 0;
        for (Status status : statuses) {
            if(timelineCount < maxCount) {
                System.out.println("UserName : "+status.getUser().getName() + ":::\nTweet : " + status.getText());
                timelineCount++;
            }else {
                break;
            }
        }
        System.out.println("***************");
    }
}
