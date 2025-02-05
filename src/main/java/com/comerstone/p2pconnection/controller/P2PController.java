package com.comerstone.p2pconnection.controller;

import com.comerstone.p2pconnection.dto.SignalDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/signal")
public class P2PController {

    @PostMapping("/offer")
    public ResponseEntity<Void> handleOffer(@RequestBody SignalDto signal) {
        log.info("Received offer signal from: {} to: {}", signal.getFrom(), signal.getTo());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/answer")
    public ResponseEntity<Void> handleAnswer(@RequestBody SignalDto signal) {
        log.info("Received answer signal from: {} to: {}", signal.getFrom(), signal.getTo());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ice-candidate")
    public ResponseEntity<Void> handleIceCandidate(@RequestBody SignalDto signal) {
        log.info("Received ICE candidate from: {} to: {}", signal.getFrom(), signal.getTo());
        return ResponseEntity.ok().build();
    }
}
