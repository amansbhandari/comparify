package ca.dal.comparify.framework.notification.websocket;

import ca.dal.comparify.framework.app.ApplicationScope;
import ca.dal.comparify.framework.notification.model.WebSocketNotificationModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Harsh Shah
 */
@Service
@Slf4j
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ApplicationScope applicationScope;


    /**
     * @param userId
     * @param model
     * @return
     *
     * @author Harsh Shah
     */
    public boolean send(String userId, WebSocketNotificationModel model) {
        send(Collections.singletonList(userId), model);
        return true;
    }

    /**
     * @param userIds
     * @param model
     * @return
     *
     * @author Harsh Shah
     */
    public boolean send(List<String> userIds, WebSocketNotificationModel model) {
        List<String> activeUsers = userIds.stream()
            .filter(userId -> applicationScope.containsActiveUsers(userId))
            .collect(Collectors.toList());

        model.setReceiverIds(activeUsers);

        simpMessagingTemplate.convertAndSend("/topic/new-alert", model);

        return true;
    }
}
