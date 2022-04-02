package ca.dal.comparify.framework.app;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Harsh Shah
 */
@Configuration
@org.springframework.web.context.annotation.ApplicationScope
public class ApplicationScope {

    private Set<String> activeUsers;

    private Map<String, String> userToReceiverToken;

    public Set<String> getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(Set<String> activeUsers) {
        this.activeUsers = activeUsers;
    }

    /**
     * @param userId
     *
     * @author Harsh Shah
     */
    public void setActiveUsers(String userId) {
        if(this.activeUsers == null){
            this.activeUsers = new HashSet<>();
        }

        this.activeUsers.add(userId);
    }

    /**
     * @param userId
     * @return
     *
     * @author Harsh Shah
     */
    public boolean containsActiveUsers(String userId) {
        if(this.activeUsers == null){
            return false;
        }

        return true;
    }

    public Map<String, String> getUserToReceiverToken() {
        return userToReceiverToken;
    }

    /**
     * @param userId
     * @return
     *
     * @author Harsh Shah
     */
    public String getUserToReceiverToken(String userId) {

        if(userToReceiverToken == null){
            return null;
        }

        return userToReceiverToken.get(userId);
    }

    public void setUserToReceiverToken(Map<String, String> userToReceiverToken) {
        this.userToReceiverToken = userToReceiverToken;
    }

    /**
     * @param userId
     * @param token
     *
     * @author Harsh Shah
     */
    public void setUserToReceiverToken(String userId, String token){
        if(this.userToReceiverToken == null){
            this.userToReceiverToken = new HashMap<>();
        }
        this.userToReceiverToken.put(userId, token);
    }
}
