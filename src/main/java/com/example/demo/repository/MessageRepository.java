package com.example.demo.repository;

import com.example.demo.domain.Message;
import com.example.demo.domain.dto.MessageDto;
import com.example.demo.domain.dto.UpdateMessageStatusDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;

@Repository
public class MessageRepository {
    private static final Logger logger = LoggerFactory.getLogger(MessageRepository.class);
    private final NamedParameterJdbcTemplate template;

    public MessageRepository(JdbcTemplate jdbcTemplate) {
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public Collection<Message> getByFromPersonId(long id) {
        return Collections.emptyList();
    }

    public Collection<Message> getByToPersonId(long id) {
        return Collections.emptyList();
    }

    public void sendMessage(MessageDto message) {

    }

    public void updateStatus(UpdateMessageStatusDto messageStatus) {

    }
}
