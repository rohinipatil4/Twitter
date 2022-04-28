package org.twitter.model;

import java.util.Date;

public class TweetResponseDTO {
    String tweetContent;
    UserDTO userDTO;
    Date createdAt;

    public String getTweetContent() {
        return tweetContent;
    }

    public void setTweetContent(String tweetContent) {
        this.tweetContent = tweetContent;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public static TweetResponseDTO toPOJO(String text, String screenName, String name, String profileImageURL, Date createdAt) {
        TweetResponseDTO tweetResponseDTO = new TweetResponseDTO();
        tweetResponseDTO.setTweetContent(text);
        tweetResponseDTO.setUserDTO(UserDTO.toPOJO(screenName, name, profileImageURL));
        tweetResponseDTO.setCreatedAt(createdAt);
        return tweetResponseDTO;
    }
}
