package com.example.demo.service;

import com.example.demo.cache.MessageCache;
import com.example.demo.domain.Message;
import com.example.demo.domain.MessageStatus;
import com.example.demo.domain.Person;
import com.example.demo.domain.dto.MessageDto;
import com.example.demo.domain.dto.UpdateMessageStatusDto;
import com.example.demo.exceptions.NotFoundStatusException;
import com.example.demo.repository.MessageRepository;
import com.example.demo.utils.HttpUtils;
import com.example.demo.utils.MappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MessageService {
    private final static Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final static String SEND_NOTIFICATION_API_PATH = "/api/notification/send-message";
    private final MessageRepository messageRepository;
    private final PersonService personService;
    private final String notificationServiceApiPath;
    private final MessageCache messageCache;

    public MessageService(MessageRepository messageRepository,
                          PersonService personService,
                          @Value("${notificationServiceApiPath}") String notificationServiceApiPath,
                          MessageCache messageCache) {
        this.messageRepository = messageRepository;
        this.personService = personService;
        this.notificationServiceApiPath = notificationServiceApiPath;
        this.messageCache = messageCache;
    }

    public Collection<Message> getByFromPersonId(long id) {
        checkPerson(id);
        return messageCache.getByFromPersonId(id);
    }

    public Collection<Message> getByToPersonId(long id) {
        checkPerson(id);
        return messageCache.getByToPersonId(id);
    }

    public void sendMessage(MessageDto message) {
        checkPerson(message.getToId());
        checkPerson(message.getFromId());
        messageRepository.sendMessage(message);
        messageCache.invalidateCacheByToPersonId(message.getToId());
        messageCache.invalidateCacheByFromPersonId(message.getFromId());
        ResponseEntity<String> response = HttpUtils
                .jsonPostRequest(notificationServiceApiPath + SEND_NOTIFICATION_API_PATH, message);
        MessageStatus status = MappingUtils.parseJsonToInstance(response.getBody(), MessageStatus.class);
        logger.debug("Status {}", status);
    }

    public void updateStatus(UpdateMessageStatusDto messageStatus) {
        if (!messageRepository.isMessageExist(messageStatus.getMessageId())) {
            throw new NotFoundStatusException("Message not found");
        }
        getByToPersonId(messageStatus.getPersonId()).stream()
                .filter(message -> message.getMessageId() == messageStatus.getMessageId())
                .findFirst()
                .orElseThrow(() -> new NotFoundStatusException("Message not found by person id"));
        messageRepository.updateStatus(messageStatus);
    }

    private void checkPerson(long id) {
        Person person = personService.getById(id);
        if (person == null) {
            throw new NotFoundStatusException("Person not found");
        }
    }
}
