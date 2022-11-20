package com.example.demo.repository;

import com.example.demo.domain.Person;
import com.example.demo.domain.dto.PersonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

@Repository
public class PersonRepository {
    private static final Logger logger = LoggerFactory.getLogger(PersonRepository.class);

    private final static String SQL_SELECT_BY_ID = "select name, age, weight, height, id, gender from person where id = :id";

    private final static String SQL_SELECT_ALL = "select name, age, weight, height, id, gender from person";

    private final static String SQL_DELETE_BY_ID = "delete from person where id = :id";

    private final static String SQL_INSERT_PERSON = "insert into person(name, age, weight, height, gender)" +
            "values (:name, :age, :weight, :height, :gender)";

    private final static String SQL_UPDATE_PERSON = "update person set age = :age, weight = :weight, height = :height," +
            " name = :name, gender = :gender where id = :id";
    private final NamedParameterJdbcTemplate template;

    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Nullable
    public Person getById(long id) {
        try {
            return template.queryForObject(
                    SQL_SELECT_BY_ID,
                    new MapSqlParameterSource("id", id),
                    new PersonRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            logger.debug("Invoke getById({}) with exception.", id, e);
        }
        return null;
    }

    public Collection<Person> getAll() {
        try {
            return template.query(SQL_SELECT_ALL, new PersonRowMapper());
        } catch (DataAccessException e) {
            logger.error("Invoke getAll() with exception.", e);
        }
        return Collections.emptyList();
    }

    public void addPerson(PersonDto person) {
        try {
            template.update(SQL_INSERT_PERSON, convertPerson(person));
        } catch (DataAccessException e) {
            logger.error("Invoke addPerson({}) with exception.", person, e);
        }
    }

    public void removePerson(long id) {
        try {
            template.update(SQL_DELETE_BY_ID, new MapSqlParameterSource("id", id));
        } catch (DataAccessException e) {
            logger.error("Invoke removePerson({}) with exception.", id, e);
        }
    }

    public boolean updatePerson(PersonDto person) {
        Long personId = person.getId();
        if (personId == null) {
            logger.warn("Invoke updatePerson({}), id is null", person);
            return false;
        }
        if (getById(personId) == null) {
            return false;
        }
        MapSqlParameterSource parameterSource = convertPerson(person);
        parameterSource.addValue("id", personId);
        try {
            template.update(SQL_UPDATE_PERSON, parameterSource);
            return true;
        } catch (DataAccessException e) {
            logger.error("Invoke updatePerson({}) with exception.", person, e);
        }
        return false;
    }

    private static MapSqlParameterSource convertPerson(PersonDto person) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", person.getName());
        parameterSource.addValue("age", person.getAge());
        parameterSource.addValue("weight", person.getWeight());
        parameterSource.addValue("height", person.getHeight());
        String genderValue = person.getGender() == null ? null : person.getGender().name();
        parameterSource.addValue("gender", genderValue);
        return parameterSource;
    }
}
