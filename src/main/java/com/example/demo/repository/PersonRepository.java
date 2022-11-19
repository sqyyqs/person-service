package com.example.demo.repository;

import com.example.demo.domain.Person;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class PersonRepository {

    private final static String SQL_SELECT_BY_NAME = "select name, age, weight, height from person where name = :name";

    private final static String SQL_SELECT_ALL = "select name, age, weight, height from person";

    private final static String SQL_DELETE_BY_NAME = "delete from person where name = :name";

    private final static String SQL_INSERT_PERSON = "insert into person(name, age, weight, height)" +
            "values (:name, :age, :weight, :height)";

    private final static String SQL_UPDATE_PERSON = "update person set age = :age, weight = :weight, height = :height," +
            " name = :name where name = :name";
    private final NamedParameterJdbcTemplate template;

    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.template = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public Person getByName(String name) {
        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("name", name);
            return template.queryForObject(SQL_SELECT_BY_NAME, parameterSource, new PersonRowMapper());
        } catch (DataAccessException e) {

        }
        return null;
    }

    public Collection<Person> getAll() {
        return template.query(SQL_SELECT_ALL, new PersonRowMapper());
    }

    public void addPerson(Person person) {
        template.update(SQL_INSERT_PERSON, convertPerson(person));
    }

    public void removePerson(String name) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", name);
        template.update(SQL_DELETE_BY_NAME, parameterSource);
    }

    public boolean updatePerson(Person person) {
        if (getByName(person.getName()) == null) {
            return false;
        }
        template.update(SQL_UPDATE_PERSON, convertPerson(person));
        return true;
    }

    private static MapSqlParameterSource convertPerson(Person person) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", person.getName());
        parameterSource.addValue("age", person.getAge());
        parameterSource.addValue("weight", person.getWeight());
        parameterSource.addValue("height", person.getHeight());
        return parameterSource;
    }
}
