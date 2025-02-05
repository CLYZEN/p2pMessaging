package com.comerstone.p2pconnection.handler;

import com.comerstone.p2pconnection.dto.SignalDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SignalHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String userId = extractUserId(session);
        sessions.put(userId, session);
        log.info("WebSocket connected for user: {}", userId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        SignalDto signal = new ObjectMapper().readValue(message.getPayload(), SignalDto.class);
        log.info("Received {} from {} to {}", signal.getType(), signal.getFrom(), signal.getTo());

        WebSocketSession targetSession = sessions.get(signal.getTo());
        if (targetSession != null && targetSession.isOpen()) {
            targetSession.sendMessage(new TextMessage(message.getPayload()));
            log.info("Forwarded {} to {}", signal.getType(), signal.getTo());
        } else {
            log.warn("Target user {} not found or not connected", signal.getTo());
        }
    }

    private String extractUserId(WebSocketSession session) {
        String path = session.getUri().getPath();
        return path.substring(path.lastIndexOf('/') + 1);
    }
}
