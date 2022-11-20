package com.example.demo.controller;

import com.example.demo.domain.Message;
import com.example.demo.domain.dto.MessageDto;
import com.example.demo.domain.dto.UpdateMessageStatusDto;
import com.example.demo.service.MessageService;
import com.example.demo.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/from-person-id")
    public ResponseEntity<Collection<Message>> getByFromPersonId(@RequestParam long id) {
        logger.info("Invoke getByFromPersonId({})", id);
        return ResponseEntity.ok(messageService.getByFromPersonId(id));
    }

    @GetMapping("/to-person-id")
    public ResponseEntity<Collection<Message>> getByToPersonId(@RequestParam long id) {
        logger.info("Invoke getByToPersonId({})", id);
        return ResponseEntity.ok(messageService.getByToPersonId(id));
    }

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDto message) {
        logger.info("Invoke sendMessage({})", message);
        messageService.sendMessage(message);
        return ResponseEntity.ok(JsonUtils.EMPTY_JSON);
    }

    @PutMapping("/update-status")
    public ResponseEntity<String> updateStatus(@RequestBody UpdateMessageStatusDto messageStatus) {
        logger.info("Invoke updateStatus({})", messageStatus);
        messageService.updateStatus(messageStatus);
        return ResponseEntity.ok(JsonUtils.EMPTY_JSON);
    }
}
