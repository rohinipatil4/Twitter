package org.twitter.common;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.Scanner;

public class TwitterUtil {
    private static void checkTwitter() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        System.out.println("Username :"+twitter.getScreenName());

        System.out.println("Welcome to Twitter\n Choose 1 to View HomeTimeLine\n Choose 2 to Publish a Tweet");
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        switch (s){
            case "1" : Timeline.getHomeTimeLine(twitter, 5);
                break;
            case "2" : System.out.println("Enter a Post content");
                String content = sc.next();
                Status status = PostTweet.postTweet(twitter, content);
                System.out.println("Successfully updated the status to [" + status.getText() + "].");
                break;
            default:
                System.out.println("Invalid Input");
                System.exit(0);
        }
    }
}
