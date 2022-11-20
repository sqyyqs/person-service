package com.example.demo.repository;

import com.example.demo.domain.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageRowMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Message(
                rs.getLong("from_id"),
                rs.getLong("to_id"),
                rs.getString("message"),
                rs.getBoolean("read"),
                rs.getLong("message_id")
        );
    }
}
