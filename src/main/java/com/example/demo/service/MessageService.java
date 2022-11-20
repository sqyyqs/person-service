package com.example.demo.service;

import com.example.demo.domain.Message;
import com.example.demo.domain.Person;
import com.example.demo.domain.dto.MessageDto;
import com.example.demo.domain.dto.UpdateMessageStatusDto;
import com.example.demo.exceptions.NotFoundStatusException;
import com.example.demo.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final PersonService personService;

    public MessageService(MessageRepository messageRepository, PersonService personService) {
        this.messageRepository = messageRepository;
        this.personService = personService;
    }

    public Collection<Message> getByFromPersonId(long id) {
        checkPerson(id);
        return messageRepository.getByFromPersonId(id);
    }

    public Collection<Message> getByToPersonId(long id) {
        checkPerson(id);
        return messageRepository.getByToPersonId(id);
    }

    public void sendMessage(MessageDto message) {
        checkPerson(message.getToId());
        checkPerson(message.getFromId());
        messageRepository.sendMessage(message);
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
