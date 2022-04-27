import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    @Mock private Twitter twitter;

    @Test
    public void checkUserName() throws TwitterException {
        twitter = TwitterFactory.getSingleton();
        String username = twitter.getScreenName();
        assertEquals("rohinipatil4", username.toLowerCase());
    }

    @Test
    public void checkTweet() throws TwitterException {
        twitter = TwitterFactory.getSingleton();
        String testTweet = "Hello world";
        Status status = twitter.updateStatus(testTweet);
        assertEquals(testTweet, status.getText());
    }
}
