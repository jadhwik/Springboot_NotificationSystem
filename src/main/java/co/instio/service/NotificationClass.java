package co.instio.service;

import co.instio.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.CloseStatus;

@Service
@Slf4j
public class NotificationClass  implements WebSocketHandler {

    @Override
    public  void afterConnectionEstablished(WebSocketSession webSocketSession) throws ServiceException{
        log.error("Connection established with session:{}",webSocketSession.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception{
        String notifications = (String) message.getPayload();
        session.sendMessage(new TextMessage("session started"+notifications));
        Thread.sleep(1000);
        session.sendMessage(new TextMessage("completed the session"+notifications ));
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Exception occured: {} on session: {}", exception.getMessage(), session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("Connection closed on session: {} with status: {}", session.getId(), closeStatus.getCode());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
