package com.example.demo.repository;

import com.example.demo.domain.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class PersonRepository {

    private final static String SQL_SELECT_BY_NAME = "select name, age, weight, height from person where name = :name";
    private final NamedParameterJdbcTemplate template;

    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public Person getByName(String name) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", name);
        return template.queryForObject(SQL_SELECT_BY_NAME, parameterSource, new PersonRowMapper());

    }

    public Collection<Person> getAll() {
        return null;
    }

    public void addPerson(Person person) {

    }

    public void removePerson(String name) {

    }

    public boolean updatePerson(Person person) {
        return false;
    }
}
