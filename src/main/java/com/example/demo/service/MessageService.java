package com.example.demo.service;

import com.example.demo.domain.Message;
import com.example.demo.domain.dto.MessageDto;
import com.example.demo.domain.dto.UpdateMessageStatusDto;
import com.example.demo.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Collection<Message> getByFromPersonId(long id) {
        return messageRepository.getByFromPersonId(id);
    }

    public Collection<Message> getByToPersonId(long id) {
        return messageRepository.getByToPersonId(id);
    }

    public void sendMessage(MessageDto message) {
        messageRepository.sendMessage(message);
    }

    public void updateStatus(UpdateMessageStatusDto messageStatus) {
        messageRepository.updateStatus(messageStatus);
    }
}
