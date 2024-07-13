package org.example.botrestapi.api;

import org.example.botrestapi.dto.LinkUpdate;
import org.springframework.http.ResponseEntity;

public interface BotApi {
    ResponseEntity<?> sendUpdate(LinkUpdate linkUpdate);
}
