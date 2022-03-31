package ca.dal.comparify.framework.notification.websocket;

import ca.dal.comparify.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Harsh Shah
 */
@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    /**
     * @author Harsh Shah
     */
    public void send() {
        simpMessagingTemplate.convertAndSend("/topic/new-alert", Collections.singletonMap("user", "name"));
    }
}
