package com.example.demo.repository;

import com.example.demo.domain.Gender;
import com.example.demo.domain.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Person(
                rs.getDouble("height"),
                rs.getDouble("weight"),
                rs.getString("name"),
                rs.getLong("age"),
                rs.getLong("id"),
                Gender.getByValue(rs.getString("gender"))
        );
    }
}
