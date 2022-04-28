package org.twitter.model;

public class UserDTO {
    String twitterScreenName;
    String name;
    String profileImageURL;

    public static UserDTO toPOJO(String screenName, String name, String profileImageURL) {
        UserDTO userDTO = new UserDTO();
        userDTO.setTwitterScreenName(screenName);
        userDTO.setName(name);
        userDTO.setProfileImageURL(profileImageURL);
        return userDTO;
    }

    public String getTwitterScreenName() {
        return twitterScreenName;
    }

    public void setTwitterScreenName(String twitterScreenName) {
        this.twitterScreenName = twitterScreenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }
}
