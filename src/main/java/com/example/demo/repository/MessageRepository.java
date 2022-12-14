package com.example.demo.repository;

import com.example.demo.domain.Message;
import com.example.demo.domain.dto.MessageDto;
import com.example.demo.domain.dto.UpdateMessageStatusDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;

@Repository
public class MessageRepository {
    private static final Logger logger = LoggerFactory.getLogger(MessageRepository.class);

    private static final String SQL_SELECT_BY_FROM_ID = "select from_id, message, to_id, message_id, " +
            " read from message where from_id = :from_id";

    private static final String SQL_SELECT_BY_TO_ID = "select from_id, message, to_id, message_id, " +
            " read from message where to_id = :to_id";

    private static final String SQL_INSERT_MESSAGE = "insert into message(from_id, message, to_id)" +
            " values(:from_id, :message, :to_id)";

    private final static String SQL_IS_EXIST_MESSAGE = "select count(message_id) from message" +
            " where message_id = :message_id";

    private final static String SQL_UPDATE_MESSAGE_STATUS = "update message set read = :read " +
            "where message_id = :message_id";

    private final NamedParameterJdbcTemplate template;

    public MessageRepository(JdbcTemplate jdbcTemplate) {
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public Collection<Message> getByFromPersonId(long id) {
        try {
            return template.query(
                    SQL_SELECT_BY_FROM_ID,
                    new MapSqlParameterSource("from_id", id),
                    new MessageRowMapper()
            );
        } catch (DataAccessException e) {
            logger.error("Invoke getByFromPersonId({}) with exception.", id, e);
        }
        return Collections.emptyList();
    }

    public Collection<Message> getByToPersonId(long id) {
        try {
            return template.query(
                    SQL_SELECT_BY_TO_ID,
                    new MapSqlParameterSource("to_id", id),
                    new MessageRowMapper()
            );
        } catch (DataAccessException e) {
            logger.error("Invoke getByToPersonId({}) with exception.", id, e);
        }
        return Collections.emptyList();
    }

    public void sendMessage(MessageDto message) {
        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("from_id", message.getFromId());
            parameterSource.addValue("to_id", message.getToId());
            parameterSource.addValue("message", message.getMessage());
            template.update(SQL_INSERT_MESSAGE, parameterSource);
        } catch (DataAccessException e) {
            logger.error("Invoke sendMessage({}) with exception", message, e);
        }
    }

    public void updateStatus(UpdateMessageStatusDto messageStatus) {
        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("read", messageStatus.isRead());
            parameterSource.addValue("message_id", messageStatus.getMessageId());
            template.update(SQL_UPDATE_MESSAGE_STATUS, parameterSource);
        } catch (DataAccessException e) {
            logger.error("Invoke updateStatus({}) with exception.", messageStatus, e);
        }
    }

    public boolean isMessageExist(long messageId) {
        try {
            Long count = template.queryForObject(
                    SQL_IS_EXIST_MESSAGE,
                    new MapSqlParameterSource("message_id", messageId),
                    Long.class
            );
            return count != null && count == 1;
        } catch (DataAccessException e) {
            logger.error("Invoke isMessageExist({}) with exception.", messageId, e);
        }
        return false;
    }

}
